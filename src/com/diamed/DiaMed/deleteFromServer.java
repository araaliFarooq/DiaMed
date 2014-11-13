package com.diamed.DiaMed;

import java.util.ArrayList;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.diamed.DiaMed.library.*;;

public class deleteFromServer extends Activity {

	
	
	DatabaseManipulator dm;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser2 jsonParser = new JSONParser2();

	
	private static final String url= "http://10.0.2.2/diamed/mobile/deleteEntry.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PID = "pid";
	
	static List<String[]> name = null;
	static String[] stg1;
	String username;
	String tablename;
	String pid;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dm = new DatabaseManipulator(deleteFromServer.this);
		
		Intent i = getIntent();
		pid = i.getStringExtra(TAG_PID);
		tablename = i.getStringExtra("tablename");

		
		try {
			name = dm.selectDocDetails();

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
		
				
				new DeleteEntry(tablename,pid,username).execute();
	
	}

	

	/*****************************************************************
	 * Background Async Task to Delete entry
	 * */
	class DeleteEntry extends AsyncTask<String, String, String> {

		String pid;
		String patientsId;
		String tableId;
		
	public DeleteEntry(String table, String entryId, String userID){
		
		this.tableId = table;
		this.pid = entryId;
		this.patientsId = userID;
		
	}	
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(deleteFromServer.this);
			pDialog.setMessage("Deleting Server Entry...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Deleting product
		 * */
		protected String doInBackground(String... args) {

			// Check for success tag
			int success;
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("tablename", tableId));
				params.add(new BasicNameValuePair("pId", pid));
				params.add(new BasicNameValuePair("username", patientsId));

				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						url, "POST", params);

				// check your log for json response
				Log.d("Delete Product", json.toString());
				
				// json success tag
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					// product successfully deleted
					// notify previous activity by sending code 100
					Intent i = getIntent();
					// send result code 100 to notify about product deletion
					setResult(100, i);
					finish();
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
			// dismiss the dialog once product deleted
			pDialog.dismiss();

		}

	}
}
