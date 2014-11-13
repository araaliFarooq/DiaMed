package com.diamed.DiaMed;



import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Window;

public class progressBar extends Activity {
	public static final int DIALOG_LOADING = 1;
	 static int Identifier;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent i = getIntent();
         Identifier = i.getIntExtra("myId", 0);
        
        //setContentView(R.layout.main);
        showDialog(DIALOG_LOADING);
        Thread thread =  new Thread(null, doSomeTask);
        thread.start();
    }

    private Runnable doSomeTask = new Runnable() {
		public void run() {
	    		try {
                        //Code of your task
	    			Thread.sleep(2000);
			} catch (InterruptedException e) {}
			//Done! now continue on the UI thread
	        runOnUiThread(taskDone);
		}
	};	

	//Since we can't update our UI from a thread this Runnable takes care of that!
    private Runnable taskDone = new Runnable() {
        public void run() {
        	dismissDialog(DIALOG_LOADING);
        	switch(Identifier){
        	
        	case 1:
        	
            	Intent x = new Intent(progressBar.this, ViewOtherDiseases.class);
    			startActivity(x);
    			
    			break;
    			
        	case 2:
        	
        	Intent i = new Intent(progressBar.this, ViewDiabetes.class);
			startActivity(i);
			
			break;
			
        	case 3:
        		
        		Intent y = new Intent(progressBar.this, ViewPressure.class);
    			startActivity(y);
    			
    			break;
    			
    			default:
        	}
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DIALOG_LOADING:
            final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //here we set layout of progress dialog
            dialog.setContentView(R.layout.progressbar);
            dialog.setCancelable(true);
            dialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					
				}
            });
        return dialog;  

        default:
            return null;
        }
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	};
    
    
}