package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.diamed.DiaMed.library.*;

public class RestoreDiabetes extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;
	private static DatabaseManipulator db;
	private static String username;
	static List<String[]> name = null;
	static String[] stg1;
	
	// Creating JSON Parser object
	JSONParser2 jParser = new JSONParser2();

	ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	private static String url;

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_RESULTS = "results";
	private static final String ID = "id";
	private static final String DATE = "Date";
	private static final String TIME = "Time";
	private static final String ACTIVITY = "Activity";
	private static final String LEVEL = "sugar_Level";
	
	// products JSONArray
	JSONArray result = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new DatabaseManipulator(RestoreDiabetes.this);
		db.deleteData3();
		new RestoreAll().execute();

	}

	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class RestoreAll extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(RestoreDiabetes.this);
			pDialog.setMessage("Restoring Diabetes BackUp. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			
			List<String[]> tableValues = null;
			
			
			try {
				name = db.selectDocDetails();

				stg1 = new String[name.size()];

				int x = 0;
				String stg;

				for (String[] name1 : name) {
					stg = name1[2];

					stg1[x] = stg;
					x++;

				}

				username = stg1[0];

			} catch (Exception e) {
				// TODO: handle exception
				Log.i("selecting username", e.toString());
			}
			url = "http://10.0.2.2/diamed/mobile/getDiabetesBackup.php";
			
			Log.i("link: ", url);
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url, "GET",
					params);
			

			// Check your log cat for JSON reponse
			 //Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					
					// Getting Array of Products
					result = json.getJSONArray(TAG_RESULTS);
					Log.i("All Products: ", result.toString());

					
					// looping through All Products
					for (int i = 0; i < result.length(); i++) {
						JSONObject c = result.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(ID);
						String date = c.getString(DATE);
						String Time = c.getString(TIME);
						String activity = c.getString(ACTIVITY);
						String sugarLevel = c.getString(LEVEL);
						
						db.insert3(date, Time, activity, sugarLevel);
						
						
					}
				} else {
					
					Log.i("RESULT TAG: ", ""+success);
					Intent i = new Intent(getApplicationContext(),
							RestoreDiabetes.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					finish();
					Intent i = new Intent(getApplicationContext(),
						RestoreOtherFiles.class);
					RestoreDiabetes.this.startActivity(i);

				}
			});

		}

	}
}