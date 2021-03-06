package com.diamed.DiaMed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BackupOtherDiseases extends Activity {

	private static final int CONNECTION_TIMEOUT = 10 * 1000;
	private static final int READ_TIMEOUT = 10 * 1000;
	String date, time, value, activity;
	private String jsonString;
	private HttpURLConnection urlConnection;
	private PrintWriter out;
	private DataObjectFile dataObjectFile;
	private List<DataObjectFile> dataObjectList;
	// url to create new product
	static String diamedURL = "http://10.0.2.2/diamed/mobile/FileToServer.php";
	static List<String[]> name = null;
	static String[] stg1;
	private static String username;
	private static String concatId;
	private ProgressDialog pDialog;
	private static int boolian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent upload = getIntent();
		boolian = upload.getIntExtra("progressbar", 0);

		DatabaseManipulator dm;
		List<String[]> tableValues = null;
		dataObjectList = new ArrayList<DataObjectFile>();

		dm = new DatabaseManipulator(BackupOtherDiseases.this);
		tableValues = dm.selectAll();

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

		for (String[] value : tableValues) {

			concatId = value[0] + username;

			dataObjectFile = new DataObjectFile(username, concatId, value[1],
					value[2], value[3], value[4], value[5], value[6]);
			dataObjectList.add(dataObjectFile);
			// new addDiaValueToServer(value[0], value[1], value[2], value[3],
			// value[4]).execute();

		}

		new addDiaValueToServer().execute();

	}

	class addDiaValueToServer extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			if (boolian == 1) {
				pDialog = new ProgressDialog(BackupOtherDiseases.this);
				pDialog.setMessage("Backing up Files. Please wait...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
			}
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			// Create The Data Holding Objects

			Gson gson = new Gson();
			jsonString = gson.toJson(dataObjectList);

			// Create Writer Output for the JSON String
			try {
				urlConnection = getBaseConnection();

				out = new PrintWriter(urlConnection.getOutputStream());
				out.print(jsonString);
				out.flush();
				out.close();
				urlConnection.connect();
				if (urlConnection.getResponseCode() == 200) {
					alert("CONNECTION RESPONSE WENT WELL");
					// Investigating the content of the json string
					alert(jsonString);
					alert(urlConnection.getResponseMessage());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					finish();
					Intent zt = new Intent(BackupOtherDiseases.this,
							ControlPanel.class);
					BackupOtherDiseases.this.startActivity(zt);

				}
			});

		}

	}

	protected HttpURLConnection getBaseConnection() throws IOException {
		HttpURLConnection connection;
		URL url;
		try {
			url = new URL(diamedURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// urlConnection.setFixedLengthStreamingMode(jsonString.getBytes().length);
			connection.setConnectTimeout(CONNECTION_TIMEOUT);
			connection.setReadTimeout(READ_TIMEOUT);
			return connection;
		} catch (MalformedURLException e) {
			throw new IOException("Malformed URL");
		}

	}

	private void alert(String message) {
		// Toast.makeText(this,message, Toast.LENGTH_LONG).show();
		Log.i("sys check", message);
	}
}
