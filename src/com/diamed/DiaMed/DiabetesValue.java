package com.diamed.DiaMed;




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



public class DiabetesValue extends Activity{
	
	public static Button sub;
    static Button close;
	public static EditText editor;

	static float value=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.tsk1);
		showAlertBox();
		
	}
	
	private void showAlertBox() {
        AlertDialog alertBox = null;
        alertBox = new AlertDialog.Builder(this).create();
        alertBox.setTitle("\t\t\t\t\t\tBlood Sugar");
        alertBox.setMessage("\t\tEnter Blood Sugar Level " );
        alertBox.setCancelable(false);

        final LayoutInflater inflater = LayoutInflater.from(this);
        final View alert_webview = inflater.inflate(R.layout.diavalue, null);
        alertBox.setView(alert_webview);
        
        sub = (Button) alert_webview.findViewById(R.id.btnsub);
        close = (Button) alert_webview.findViewById(R.id.btnclose);
        editor = (EditText) alert_webview.findViewById(R.id.editTxt);
	   
	    
	    TextWatcher watcher = new LocalTextWatcher();
	    editor.addTextChangedListener(watcher);
	    
	    
	  //activates the continue button...
	    updateButtonState();
	    
       
        close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(DiabetesValue.this,MainMenu.class);
				DiabetesValue.this.startActivity(t);
				
			}
		});
        
        
        sub.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final int vId = v.getId();
                switch (vId) {
                case R.id.btnsub:
                	String val = editor.getText().toString();
					
					
					if(val!=" "){
						
                	try{
                	value = Float.parseFloat(val);
					
              		
                		Intent x = new Intent(DiabetesValue.this,ActivityBeforeDiabetesReading.class);
                		x.putExtra("key1",value);
                		x.putExtra("key2",val);
						DiabetesValue.this.startActivity(x);
                	
                	
                	
                	}catch(NumberFormatException ee){
                		Log.i("Exception", ee.toString());
                		Toast.makeText(DiabetesValue.this, "Wrong Or No Entry Made.Try Again", Toast.LENGTH_SHORT).show();
                		
                	}
                	
                	}else{
                		
                	
                		
                		
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
	}
	
	void updateButtonState() {
	    
	}

}

class LocalTextWatcher implements TextWatcher {

	

	private void updateButtonState() {
		// TODO Auto-generated method stub
		boolean enabled = checkEditText(DiabetesValue.editor);
		DiabetesValue.sub.setEnabled(enabled);
		
	}

	
	private boolean checkEditText(EditText edit) {
	    return ((edit.getText().toString()).length() >0 );
	}


	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		updateButtonState();
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
    
}



