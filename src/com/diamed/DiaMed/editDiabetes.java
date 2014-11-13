package com.diamed.DiaMed;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class editDiabetes extends ListActivity {

	AlertDialog alertBox = null;
	private static int rowID;
	static Button cancel;
	static Button continu;
	static EditText text;

	static String sugar;
	static String origValue = null;
	static List<String[]> names2 = null;
	static String[] stg1;
	static String level;
static int idz;
	DatabaseManipulator dm2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent x = getIntent();
		rowID = x.getIntExtra("key1", 0);
		Log.i("key1", "" + rowID);
		
		showAlertBox();
	}

	private void showAlertBox() {

		dm2 = new DatabaseManipulator(this);

		alertBox = new AlertDialog.Builder(this).create();
		alertBox.setTitle("\t\tEdit  Value");
		alertBox.setCancelable(false);
		//alertBox.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

		final LayoutInflater inflater = LayoutInflater.from(this);
		final View alert_webview = inflater.inflate(R.layout.edit, null);
		alertBox.setView(alert_webview);

		cancel = (Button) alert_webview.findViewById(R.id.button2);
		continu = (Button) alert_webview.findViewById(R.id.button1);

		Log.i("sugar2", "" + rowID);

		try {
			names2 = dm2.selectSugarLevel(rowID);

			stg1 = new String[names2.size()];

			int x = 0;
			String stg;

			for (String[] name : names2) {
				stg = name[4];

				stg1[x] = stg;
				x++;

			}

			
			Log.i("stg1", stg1[0]);
			text = (EditText) alert_webview.findViewById(R.id.sugarLevel);

			text.setText(stg1[0]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("pick for editing", e.toString());
		}

		// public void onListItemClick(ListView parent, View v, int position,
		// long id) {

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent con = new Intent(editDiabetes.this, ViewDiabetes.class);
				alertBox.dismiss();
				editDiabetes.this.startActivity(con);

			}
		});

		continu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				String sugarLevel;

				sugar = text.getText().toString();
				Log.i("edited", sugar);

				if (dm2.updateDiabetes(rowID, sugar)) {

					Toast.makeText(getApplicationContext(),
							"Operation Successful", Toast.LENGTH_LONG).show();
					Thread minawa = new Thread(){
			    		public void run(){
			    			try{
			    				
			    				Intent con = new Intent(editDiabetes.this,
										ViewDiabetes.class);
			    				alertBox.dismiss();
								editDiabetes.this.startActivity(con);
			    				sleep(2500);
			    			}catch(InterruptedException ee){
			    				ee.printStackTrace();
			    			}finally{
			    				
			    				names2 = dm2.selectSugarLevel(rowID);

			    				stg1 = new String[names2.size()];

			    				int x = 0;
			    				String stg;

			    				for (String[] name : names2) {
			    					stg = name[3];

			    					stg1[x] = stg;
			    					x++;

			    				}
			    				
			    				String activity = stg1[0];
			    				
			    				if(activity.equals("Before A Meal")){
			    					idz = 1;
			    				}
			    				else if (activity.equals("After A Meal")){
			    					
			    					idz = 2;
			    					
			    				}else if(activity.equals("Bed Time")){
			    					
			    					idz = 3;
			    				}
			    			
			    				float level = Float.parseFloat(sugar);
			    				Intent y = new Intent(editDiabetes.this,RecomendationForDiabetes.class);
			    				y.putExtra("key2", level);
								y.putExtra("key3",idz);

			    				editDiabetes.this.startActivity(y);
			    			}
			    			
			    		}
			    	 };
			    	 minawa.start();
					
				} else {

					Toast.makeText(getApplicationContext(),
							"Operation NOT Successful", Toast.LENGTH_LONG).show();
					Intent con = new Intent(editDiabetes.this,
							ViewDiabetes.class);
					editDiabetes.this.startActivity(con);

				}
			}
		});

		alertBox.show();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//finish();
	}

}
