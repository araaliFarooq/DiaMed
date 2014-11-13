package com.diamed.DiaMed;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HbPressureOptions extends Activity{
	
	TextView selection;
	public int idToModify; 
	DatabaseManipulator dm;
	static  Button options;
	String fontPath = "fonts/MyriadPro-BoldCond.otf";
	static Typeface tf ;
	String postn;
	Button del;
	Button deleteall;
	Button cancel;
	Button edit;
	static final int DIALOG_ID = 0;
	static int identifier;
    static int rowId;
    MessageInRow Detail;
    protected AlertDialog alertBox = null;

    Boolean isInternetPresent = false;
	ConnectionDetector connection;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent x = getIntent();
         rowId= x.getIntExtra("key", 0);
		showAlertBox();
	}


	public void showAlertBox() {
        
        alertBox = new AlertDialog.Builder(this).create();
        alertBox.setTitle("Update  Options");
        alertBox.setCancelable(true);

        final LayoutInflater inflater = LayoutInflater.from(this);
        final View alert_webview = inflater.inflate(R.layout.deleteoptions, null);
        alertBox.setView(alert_webview);
        
        
        edit = (Button) alert_webview.findViewById(R.id.button0);
        del = (Button) alert_webview.findViewById(R.id.button1);
        deleteall = (Button) alert_webview.findViewById(R.id.button2);
       
        cancel = (Button) alert_webview.findViewById(R.id.button3);
        
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent con = new Intent(HbPressureOptions.this,ViewPressure.class);
				HbPressureOptions.this.startActivity(con);
				alertBox.dismiss();
				
			}
		});
	    
	    
       
        del.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isInternetPresent = connection.isConnectingToInternet();
	        	  
	        	  if(isInternetPresent){
	        		  
	        	  showDialog(DIALOG_ID);
	        	 
	        	  }else{
	        		  
	        		  showAlertDialog(HbPressureOptions.this, "No Internet Connection",
								"Connect to Internet.", false);  
	        	  }
			}
		});
        
        
        deleteall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
            	
            	Toast.makeText(getApplicationContext(), "Confirm First", Toast.LENGTH_LONG).show();
				Intent con = new Intent(HbPressureOptions.this,confirm2.class);
				HbPressureOptions.this.startActivity(con);
            }
    } );
        
        edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

        		Intent x = new Intent(HbPressureOptions.this,editPressure.class);
        		x.putExtra("key1",rowId);
        		Log.i("Sending", ""+rowId);
        		HbPressureOptions.this.startActivity(x);
				
			}
		});
        
        alertBox.show();

	}
	
	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch(id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("\t\tConfirm Deletion")
			.setCancelable(false)
			.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					String tablename = "hbpressure";
					dm.deleteItem4(rowId);
				
					Intent i = new Intent(HbPressureOptions.this, deleteFromServer.class);
					alertBox.dismiss();
					i.putExtra("pid",rowId);
					i.putExtra("tablename", tablename);
					startActivity(i);

              }
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});
			AlertDialog alert = builder.create(); 
			dialog = alert;
			break;

		default:

		}
		return dialog;
	}
	
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);
		
		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
}
