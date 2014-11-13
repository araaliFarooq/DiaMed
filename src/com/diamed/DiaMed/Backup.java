package com.diamed.DiaMed;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class Backup extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		showAlertDialog(Backup.this, "\t\t\t\t\t\t\tBACKUP",
				"\t\t\tBackup File Will Be Created.", false);
		
	}
	
	
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
		final AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);
		
		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.success);

		// Setting OK Button
		
		
		alertDialog.setButton("PROCEED", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
				int nmbr = 1;
				Intent i = new Intent(Backup.this, BackupBloodPressure.class);
				i.putExtra("progressbar", nmbr);
				Backup.this.startActivity(i);
				alertDialog.dismiss();
			}
		});
		
		alertDialog.setButton2("CANCEL", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(Backup.this, ControlPanel.class);
				Backup.this.startActivity(i);
				alertDialog.dismiss();
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
