package com.diamed.DiaMed;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class presSpeak extends Activity implements OnInitListener {
    
    private int MY_DATA_CHECK_CODE = 0;

    static float systolic;

	 static float diastolic;
	 static int radioId;
    private TextToSpeech tts;
    
    static TextView outPut;
    static EditText inputText;
    static String text = null;
    static AlertDialog alertBox;
    static Button back, listen, finish; 
    //static Button speakButton;
    
 @Override
 public void onCreate(Bundle savedInstanceState) {
    
  super.onCreate(savedInstanceState);
  
  Intent i = getIntent();
  text = i.getStringExtra("rec");
  systolic = i.getFloatExtra("sys", 0);
  diastolic = i.getFloatExtra("dia",0);
  radioId = i.getIntExtra("radio",0);
  
  
  
  showAlertBox();
  
  /*try {
	speak.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (text!=null && text.length()>0) {
			    Toast.makeText(presSpeak.this, "Saying: " + text, Toast.LENGTH_LONG).show();
			    try {
					tts.speak(text, TextToSpeech.QUEUE_ADD, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.i("tts",e.toString());
				}
			       }
			   
		}
	});
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/
	       //String text = inputText.getText().toString();
		 
  
	     

  
  Intent checkIntent = new Intent();
      checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
      startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
        
    }
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                tts = new TextToSpeech(this, this);
            } 
            else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }

    }

    @Override
    public void onInit(int status) {        
        if (status == TextToSpeech.SUCCESS) {
            Toast.makeText(presSpeak.this, 
                    "Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
        }
        else if (status == TextToSpeech.ERROR) {
            Toast.makeText(presSpeak.this, 
                    "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
        }
    }
   

	private void showAlertBox() {
      
		
		
        final AlertDialog alertBox = new AlertDialog.Builder(this).create();
        alertBox.setTitle("Recommendation");
        alertBox.setCancelable(false);
      
       

        final LayoutInflater inflater = LayoutInflater.from(this);
        final View alert_webview = inflater.inflate(R.layout.speakxml, null);
        alertBox.setView(alert_webview);
        
         back = (Button)alert_webview.findViewById(R.id.tryButton);
        listen = (Button) alert_webview.findViewById(R.id.listen);
        finish = (Button) alert_webview.findViewById(R.id.exit);
        outPut = (TextView) alert_webview.findViewById(R.id.output);
        
       

			outPut.setText(text);	
            
            

    		
           
              back.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				Intent con = new Intent(presSpeak.this,RecomendationForPressure.class);
    				con.putExtra("key5", systolic);
    				con.putExtra("key6", diastolic);
    				con.putExtra("key7", radioId);
    				presSpeak.this.startActivity(con);
    				
    			}
    		});
    	    
    	    
          
            
            
            listen.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                	if (text!=null && text.length()>0) {
        			    try {
        					tts.speak(text, TextToSpeech.QUEUE_ADD, null);
        				} catch (Exception e) {
        					// TODO Auto-generated catch block
        					Log.i("tts",e.toString());
        				}
        			       }
                	
                }
        } );
            
            finish.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(tts != null){
						tts.stop();
						tts.shutdown();
							}
					alertBox.dismiss();
					   finish();
					
					Intent con = new Intent(presSpeak.this,MainMenu.class);
    				presSpeak.this.startActivity(con);
					
				}
			});
            
            alertBox.show();

    	
 	
}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//alertBox.dismiss();
		//finish();
	
	}
	
}