package com.diamed.DiaMed;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AutoSms extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
     //setContentView(R.layout.main);

      /*Button startBtn = (Button) findViewById(R.id.sendSMS);
      startBtn.setOnClickListener(new View.OnClickListener() {
         public void onClick(View view) {
         
      }
   });*/

      sendSMS();
      
   }
   protected void sendSMS() {
      Log.i("Send SMS", "");

      Intent smsIntent = new Intent(Intent.ACTION_VIEW);
      smsIntent.setData(Uri.parse("smsto:"));
      smsIntent.setType("vnd.android-dir/mms-sms");

      smsIntent.putExtra("address"  , new String ("0700953767"));
      smsIntent.putExtra("sms_body"  , "Test SMS to Doctor");
      try {
         startActivity(smsIntent);
         finish();
         Log.i("Finished sending SMS...", "");
      } catch (android.content.ActivityNotFoundException ex) {
         Toast.makeText(AutoSms.this, 
         "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
      }
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.intro, menu);
      return true;
   }
}