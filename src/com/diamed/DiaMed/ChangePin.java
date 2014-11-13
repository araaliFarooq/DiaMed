package com.diamed.DiaMed;





import android.app.Activity;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.content.Intent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
//import android.text.InputType;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
//import android.widget.ToggleButton;

public class ChangePin extends Activity {

	//protected DbHelp db =null;
	protected SQLiteDatabase mDB = null;
	protected Cursor mCursor = null;
	Database dbase;
	
	String pin1;
	String pin2;
	String pin3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change);

		final EditText current = (EditText) findViewById(R.id.pinOld);
		final EditText altered = (EditText) findViewById(R.id.pin);
		final EditText confirm = (EditText) findViewById(R.id.confirm);
		final Button submit = (Button) findViewById(R.id.set);
		final Button cloze = (Button) findViewById(R.id.cancel2);
		final ToggleButton toggo = (ToggleButton) findViewById(R.id.toggleButton1);
		dbase = new Database(this);
		dbase.open();
		
		toggo.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (toggo.isChecked()) {
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
		
		submit.setOnClickListener(new View.OnClickListener() {
					
			public void onClick(View v) {
				
				pin1=altered.getText().toString();
				pin2=confirm.getText().toString(); 
				pin3= current.getText().toString();
				
				//Toast.makeText(getApplicationContext(),pin1+" = "+pin2,Toast.LENGTH_SHORT).show();
					//String dbPin = dbase.fetchPin().toString();
					if(pin3.equals(dbase.fetchPin())){
		   if((pin1.equals(pin2)) ){
			   boolean success = dbase.delete();
				long rowId=dbase.addPIN(pin1);
			  
				if(rowId!=-1 && success == true){
					Toast.makeText(getApplicationContext(),pin1+"  has replaced  "+pin3,Toast.LENGTH_SHORT).show();
					
					startActivity(new Intent(ChangePin.this, LoginUser.class));
					//startActivity(new Intent(getApplicationContext(),Diamed.class));
				}
				else{
					Toast.makeText(getApplicationContext(),"Process Failed",Toast.LENGTH_SHORT).show();
				}
			}
			else{
				Toast.makeText(getApplicationContext(),"PINS do not match",Toast.LENGTH_SHORT).show();
			}
					}else{
						
						Toast.makeText(getApplicationContext(),"current pin is not correct",Toast.LENGTH_SHORT).show();
					}
						
					}
		});
		
		
		

		cloze.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent panel = new Intent(ChangePin.this, ControlPanel.class);
				ChangePin.this.startActivity(panel);
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
