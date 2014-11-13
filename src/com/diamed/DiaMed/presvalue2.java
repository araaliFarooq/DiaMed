package com.diamed.DiaMed;

import com.diamed.DiaMed.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class presvalue2 extends Activity{
	
	public static Button sub;
    static Button close;
	public static EditText editor;
	public static EditText editor2;
	static float value=0;
	static float value2=0;
	View alert_webview = null;
	AlertDialog alertBox = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.tsk1);
		showAlertBox();
	}
	
	private void showAlertBox() {
      
        alertBox = new AlertDialog.Builder(this).create();
        alertBox.setTitle("\t\t\t\t\t\tBlood Pressure");
        alertBox.setMessage("\tEnter Blood Pressure Level.");
        alertBox.setCancelable(false);

        final LayoutInflater inflater = LayoutInflater.from(this);
        alert_webview = inflater.inflate(R.layout.presvalue, null);
        alertBox.setView(alert_webview);
        
        sub = (Button) alert_webview.findViewById(R.id.btnsub);
        close = (Button) alert_webview.findViewById(R.id.btnclose);
        editor = (EditText) alert_webview.findViewById(R.id.editTxt);
	    editor2 = (EditText) alert_webview.findViewById(R.id.editTxt2);
	    
	    TextWatcher watcher = new LocalTextWatcher1();
	    editor.addTextChangedListener(watcher);
	    editor2.addTextChangedListener(watcher);
	    updateButtonState();
	    
        
        close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(presvalue2.this,MainMenu.class);
				presvalue2.this.startActivity(t);
				
			}
		});
        
        sub.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final int vId = v.getId();
                switch (vId) {
                case R.id.btnsub:
                	String val = editor.getText().toString();
					String val2 = editor2.getText().toString();
					
					if(val!=" " && val2!=" "){
						
                	try{
                	value = Float.parseFloat(val);
					value2 = Float.parseFloat(val2);
              		
                		Intent x = new Intent(presvalue2.this,ActivityBeforePressureReading.class);
                		x.putExtra("key1",val);
						x.putExtra("key2",val2);
						x.putExtra("key3", value);
						x.putExtra("key4", value2);
						presvalue2.this.startActivity(x);
                	System.out.println(value);
                	System.out.println(value2);
                	
                	}catch(NumberFormatException ee){
                		Log.i("Exception", ee.toString());
                		Toast.makeText(presvalue2.this, "Wrong Or No Entry Made.Try Again", Toast.LENGTH_SHORT).show();
                		
                	}
                	
                	}else{
                		
                	
                		Toast.makeText(presvalue2.this, "In continue", Toast.LENGTH_SHORT).show();
                		
                		
                	}
                	
                    Log.i("PRESSURE_LEVEL",""+value);
                    break;
                default:
                    break;
                }
        
            }
    } );
        alertBox.show();

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		alertBox.dismiss();
		
	}
	
	void updateButtonState() {
	    
	}

}

class LocalTextWatcher1 implements TextWatcher {

	@Override
	public void afterTextChanged(Editable s) {
		
		// TODO Auto-generated method stub
		  updateButtonState();
		
	}

	private void updateButtonState() {
		// TODO Auto-generated method stub
		boolean enabled = checkEditText(presvalue2.editor)
        && checkEditText(presvalue2.editor2);
    presvalue2.sub.setEnabled(enabled);
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	private boolean checkEditText(EditText edit) {
	    return ((edit.getText().toString()).length() >0 );
	}
    
}



