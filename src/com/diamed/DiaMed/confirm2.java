package com.diamed.DiaMed;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class confirm2 extends Activity implements OnClickListener{
		
	EditText pin ;
	Button login, cancel, change;
	Database dbase;
	DatabaseManipulator dm;
	static int identifier;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//enable or disable authentication..
	
	
		setContentView(R.layout.dia_login);
		
		pin = (EditText)findViewById(R.id.ltext1);	
		login=(Button)findViewById(R.id.button0);	
		cancel=(Button)findViewById(R.id.cancel);	
		//change =(Button)findViewById(R.id.change);
		
		
		login.setOnClickListener(this); 
		cancel.setOnClickListener(this);
		//change.setOnClickListener(this);
	

	}
	

	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.button0:
		
			
			try {
				String pinNumber = pin.getText().toString();
				dbase = new Database(confirm2.this);
				@SuppressWarnings("unused")
				String value =null;
				
				dbase.open();
				
					if(dbase.fetchPin().equals(pinNumber)){
						
						identifier = 3;
						dm = new DatabaseManipulator(this);
						dm.deleteData4();
						
						Intent i = new Intent(confirm2.this, progressBar.class);
						i.putExtra("myId",identifier);
						startActivity(i);
						
					}else{
						Toast.makeText(getApplication(), "Enter correct pin please",Toast.LENGTH_SHORT).show();
					}
				
			} catch (SQLiteException e) {
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
			Intent openMenu = new Intent(confirm2.this,ViewDiabetes.class);
			confirm2.this.startActivity(openMenu);
			break;
		/*case R.id.change:
			Intent changePin = new Intent("com.Diamed.Change");
			startActivity(changePin);
			break;*/
		}
		
		}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
	
	
	
}