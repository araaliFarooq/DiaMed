package com.diamed.DiaMed;



import com.diamed.DiaMed.tutorial.TutorialActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class LoginUser extends Activity implements OnClickListener{
		
	EditText pin ;
	Button login, cancel, change , panel, createPin;
	Database dbase;
	ToggleButton toggle;
	private static Prefs prefs;
	private static boolean userTutorial;
	/*T register;
	TextView createPin;*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		prefs = new Prefs();
		
		//enable or disable authentication..
	
	boolean Enabled =prefs.getAuthenticationPref(getApplicationContext());
	userTutorial = prefs
			.startUserDemoOnStartUp(getApplicationContext());
	
	if(Enabled){
		
		setContentView(R.layout.dia_login);
		
		pin = (EditText)findViewById(R.id.ltext1);
		
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		 
		imm.showSoftInput(pin, 0);
		
		InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		 
		imm2.hideSoftInputFromWindow(pin.getWindowToken(), 0);
		login=(Button)findViewById(R.id.button0);	
		cancel=(Button)findViewById(R.id.cancel);	

		toggle = (ToggleButton)findViewById(R.id.toggleButton1);
		panel = (Button)findViewById(R.id.registerbtn);
		createPin  = (Button)findViewById(R.id.createbtn);
		
		toggle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (toggle.isChecked()) {
					pin.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				} else {
					pin.setInputType(InputType.TYPE_CLASS_TEXT);
				}

			}
		});
		
		panel.setOnClickListener(this);
		createPin.setOnClickListener(this);
		/*panel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent panelx = new Intent(Login.this,LoginActivity.class);
				Login.this.startActivity(panelx);
				Log.i("server login", "server login");

			}
		});*/
		/*createPin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent changePin = new Intent(Login.this,Change.class);
				Login.this.startActivity(changePin);
				
			}
		});*/
		login.setOnClickListener(this); 
		cancel.setOnClickListener(this);
		//change.setOnClickListener(this);
	}else{

		if(userTutorial){
			
			Intent demo = new Intent(LoginUser.this, TutorialActivity.class);
			LoginUser.this.startActivity(demo);
			
		}else{
			
		Intent changePin = new Intent(LoginUser.this,MainMenu.class);
		LoginUser.this.startActivity(changePin);
	
	   }
	}
	}
	

	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.button0:
		
			
			try {
				String pinNumber = pin.getText().toString();
				dbase = new Database(LoginUser.this);
				@SuppressWarnings("unused")
				String value =null;
				
				dbase.open();
				if((dbase.fetchPin())==null){
					
					Intent changePin = new Intent(LoginUser.this,CreateUserPin.class);
					LoginUser.this.startActivity(changePin);
				/*dbase.addItem(pinNumber);
				
				dbase.close();*/}
				else{
					if(dbase.fetchPin().equals(pinNumber)){

						if (userTutorial) {
							Intent demo = new Intent(LoginUser.this, TutorialActivity.class);
							LoginUser.this.startActivity(demo);

						} else{
						
						Intent openMenu = new Intent(LoginUser.this,MainMenu.class);
						LoginUser.this.startActivity(openMenu);
						
						}
						
					}else{
						Toast.makeText(getApplication(), "Enter correct pin please",Toast.LENGTH_SHORT).show();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				
				Dialog pop = new Dialog(this);
				pop.setTitle("No pin Submitted");
				
				TextView msg = new TextView(this);
				msg.setText("Error submiting!" + e.getMessage());
				
				pop.setContentView(msg);
				pop.show();
				
			}/*finally{
				if(!successful){
					Toast.makeText(getApplication(), "Enter correct pin please",Toast.LENGTH_SHORT).show();
				}
				}
					
					Dialog pop = new Dialog(this);
					pop.setTitle("pin Submitted");
					
					TextView msg = new TextView(this);
					msg.setText(" submitted");
					
					pop.setContentView(msg);
					pop.show();
					//String pinNumber =pin.getText().toString();
					
					//if(pin.getText().toString().equalsIgnoreCase(dbase.fetchPin(pinNumber))){
					Intent openMenu = new Intent("com.Diamed.Menu");
					startActivity(openMenu);
			}
				
				
				
			}*/
			break;
		case R.id.cancel:
			finish();
			break;

		case R.id.registerbtn:
            Intent panelx = new Intent(LoginUser.this,LoginServer.class);
            LoginUser.this.startActivity(panelx);
			break;

		case R.id.createbtn:
            Intent changePin = new Intent(LoginUser.this,CreateUserPin.class);
            LoginUser.this.startActivity(changePin);
			break;
		}
		
		}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
	
	
	
}