package com.diamed.DiaMed;

import java.util.List;

import android.app.Activity;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import android.os.Bundle;
//import android.text.InputType;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.ToggleButton;
import android.widget.ToggleButton;

public class editDoc extends Activity {

	DatabaseManipulator dbase;
	String name, username, docname, docusername, doccontact, docaddress;
	static List<String[]> names2 = null;
	static String[] stg1;
	static String[] stg2;
	static String[] stg3;
	static String[] stg4;
	static String[] stg5;
	static String[] stg6;
	String s1;
	String s2;
	String s3;
	String s4;
	String s5;
	String s6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editdoc);

		// final EditText current = (EditText) findViewById(R.id.currentpin);
		final EditText pname = (EditText) findViewById(R.id.patientsName);
		final EditText pusername = (EditText) findViewById(R.id.patientsUserName);
		final EditText dname = (EditText) findViewById(R.id.doctorsName);
		final EditText dusername = (EditText) findViewById(R.id.doctorsUserName);
		final EditText dcontact = (EditText) findViewById(R.id.doctorsContact);
		final EditText daddress = (EditText) findViewById(R.id.doctorsAddress);
		final Button submit = (Button) findViewById(R.id.btnSubmit);
		final Button cancel = (Button) findViewById(R.id.btnExit);
		final Button delete = (Button) findViewById(R.id.btnDelete);
		// final ToggleButton toggo = (ToggleButton)
		// findViewById(R.id.togglepss);

		dbase = new DatabaseManipulator(this);
		try {
			names2 = dbase.selectDocDetails();

			stg1 = new String[names2.size()];
			stg2 = new String[names2.size()];
			stg3 = new String[names2.size()];
			stg4 = new String[names2.size()];
			stg5 = new String[names2.size()];
			

			int x = 0;

			for (String[] name : names2) {
				s1 = name[1];
				s2 = name[2];
				s3 = name[3];
				s4 = name[4];
				s5 = name[5];
				

				x++;

			}

			pname.setText(s1);
			pusername.setText(s2);
			dname.setText(s3);
			
			dcontact.setText(s4);
			daddress.setText(s5);

		} catch (Exception e) {
			// TODO Auto-generated catch block

			Log.i("pick for editing", e.toString());
		}

		submit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				name = pname.getText().toString();
				username = pusername.getText().toString();
				docname = dname.getText().toString();
				
				doccontact = dcontact.getText().toString();
				docaddress = daddress.getText().toString();

				boolean isAffected;
				try {
					int id = 1;
					isAffected = dbase.updateDocRegister(id, name,
							username, docname, doccontact, docaddress);
				

				if (isAffected) {

					Toast.makeText(getApplicationContext(),
							"Records Changed Successfully", Toast.LENGTH_LONG)
							.show();
					Intent back = new Intent(editDoc.this, ControlPanel.class);
					editDoc.this.startActivity(back);

				} else {

					Toast.makeText(getApplicationContext(),
							"Failed To Change Records", Toast.LENGTH_LONG)
							.show();
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.i("editing doctor",e.toString());
				}
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent home = new Intent(editDoc.this, MainMenu.class);
				editDoc.this.startActivity(home);
			}
		});

		delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					
					dbase.deleteDocDetails();
					Intent pnl = new Intent(editDoc.this, ControlPanel.class);
					editDoc.this.startActivity(pnl);
					Toast.makeText(getApplicationContext(),
							"Records Deleted Successfully", Toast.LENGTH_LONG)
							.show();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.i("deleting doc details",e.toString());
					Toast.makeText(getApplicationContext(),
							"Records Not Deleted", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
