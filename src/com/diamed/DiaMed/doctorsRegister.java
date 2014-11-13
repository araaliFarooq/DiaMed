package com.diamed.DiaMed;

import android.app.Activity;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import android.os.Bundle;
//import android.text.InputType;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.ToggleButton;
import android.widget.ToggleButton;

public class doctorsRegister extends Activity {

	DatabaseManipulator dbase;
	String name, username, docname, docusername, doccontact, docaddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerdoc);

		// final EditText current = (EdintText) findViewById(R.id.currentpin);
		final EditText pname = (EditText) findViewById(R.id.patientsName);
		final EditText pusername = (EditText) findViewById(R.id.patientsUserName);
		final EditText dname = (EditText) findViewById(R.id.doctorsName);
		
		final EditText dcontact = (EditText) findViewById(R.id.doctorsContact);
		final EditText daddress = (EditText) findViewById(R.id.doctorsAddress);
		final Button submit = (Button) findViewById(R.id.btnRegister);
		final Button edit = (Button) findViewById(R.id.btnEdit);
		// final ToggleButton toggo = (ToggleButton)
		// findViewById(R.id.togglepss);

		dbase = new DatabaseManipulator(this);

		submit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				name = pname.getText().toString();
				username = pusername.getText().toString();
				docname= dname.getText().toString();
				
				doccontact = dcontact.getText().toString();
				docaddress = daddress.getText().toString();
				
				int checker = dbase.rowCount();
				
				if(checker > 0){
					
					Toast.makeText(getApplicationContext(), "ONLY ONE DOCTOR CAN BE REGISTERED", Toast.LENGTH_LONG).show();
				}else{
				
				dbase.insert5(name, username, docname, doccontact, docaddress);
				
				Toast.makeText(getApplicationContext(), "DOCTOR SUCCESSFULLY ADDED", Toast.LENGTH_LONG).show();
				
				Intent panel = new Intent(doctorsRegister.this, ControlPanel.class);
				doctorsRegister.this.startActivity(panel);
				
				}

			}
		});

		edit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent editor = new Intent(doctorsRegister.this, editDoc.class);
				doctorsRegister.this.startActivity(editor);
			}
		});

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		/*Intent panel = new Intent(doctorsRegister.this, MainMenu.class);
		doctorsRegister.this.startActivity(panel);*/
	}

}
