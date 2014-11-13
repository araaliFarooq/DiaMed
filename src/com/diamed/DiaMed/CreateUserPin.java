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

public class CreateUserPin extends Activity {

	
	Database dbase;
	
	String pin1;
	String pin2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dia_pin);

		//final EditText current = (EditText) findViewById(R.id.currentpin);
		final EditText altered = (EditText) findViewById(R.id.pin);
		final EditText confirm = (EditText) findViewById(R.id.confirm);
		final Button submit = (Button) findViewById(R.id.set);
		final Button cloze = (Button) findViewById(R.id.cancel2);
		//final ToggleButton toggo = (ToggleButton) findViewById(R.id.togglepss);
		
       final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton1);
		
		toggle.setOnClickListener(new View.OnClickListener() {//toggle button...
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (toggle.isChecked()) {
					altered.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					
					confirm.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				} else {
					altered.setInputType(InputType.TYPE_CLASS_TEXT);
					confirm.setInputType(InputType.TYPE_CLASS_TEXT);
				}
			}
		});
		dbase = new Database(this);
		dbase.open();
		
		submit.setOnClickListener(new View.OnClickListener() {
					
			public void onClick(View v) {
				
				pin1=altered.getText().toString();
				pin2=confirm.getText().toString();
				
				//Toast.makeText(getApplicationContext(),pin1+" = "+pin2,Toast.LENGTH_SHORT).show();
							
		   if(pin1.equals(pin2)){
				long rowId=dbase.addPIN(pin1);
				if(rowId!=-1){
					//Toast.makeText(getApplicationContext(),rowId+" ROWID FROM DB",Toast.LENGTH_SHORT).show();

					Intent changePin = new Intent(CreateUserPin.this,LoginUser.class);
					CreateUserPin.this.startActivity(changePin);
				}
				else{
					Toast.makeText(getApplicationContext(),"Save Failed",Toast.LENGTH_SHORT).show();
				}
			}
			else{
				Toast.makeText(getApplicationContext(),"PINS do not match",Toast.LENGTH_SHORT).show();
			}
						
			}
		});
		
		
		

		cloze.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
