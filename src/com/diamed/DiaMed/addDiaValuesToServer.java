package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.diamed.DiaMed.library.JSONParser2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toast;
import android.content.Intent;

class addDiaValueToServer extends AsyncTask<String, String, String> {

	String date, time, value, activity;
	// url to create new product
	static String url_create_product = "http://10.0.2.2/diamed/android_login_api/insertAndUpdate.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	JSONParser2 jsonParser = new JSONParser2();
	DatabaseManipulator dm;
	Context context;
	List<String[]> tableValues = null;

	/*
	 * public addDiaValueToServer(String _Date, String _time, String _activity,
	 * String _value){
	 * 
	 * this.date = _Date; this.time = _time; this.activity =_activity;
	 * this.value = _value;
	 * 
	 * }
	 */

	/**
	 * Creating product
	 * */
	protected String doInBackground(String... args) {
		
		dm = new DatabaseManipulator(context);
		tableValues = dm.selectAll3();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		for (String[] value : tableValues) {
			
		// Building Parameters
		
		params.add(new BasicNameValuePair("pId", value[0]));
		params.add(new BasicNameValuePair("Date", value[1]));
		params.add(new BasicNameValuePair("Time", value[2]));
		params.add(new BasicNameValuePair("activity", value[3]));
		params.add(new BasicNameValuePair("suger_Level", value[4]));

		}
		// getting JSON Object
		// Note that create product url accepts POST method
		JSONObject json = jsonParser.makeHttpRequest(url_create_product,
				"POST", params);
		
		// check log cat fro response
		Log.d("Create Response", json.toString());

		// check for success tag
		try {
			int success = json.getInt(TAG_SUCCESS);

			if (success == 1) {
				// successfully created product

            Log.i("successful", " data added");
				
			} else {
				// failed to create product
				 Log.i("failure", " data addition failed");
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
		// dismiss the dialog once done
		// finish();
	}

}