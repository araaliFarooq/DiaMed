package com.diamed.DiaMed;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Trigger Status bar Notification alert at particular date and time
 * 
 * 
 */
public class Reminder extends BroadcastReceiver {
	private NotificationManager mNotificationManager;
	private Notification notification;
	long[] vibration = { 1000, 1000, 1000, 1000, 1000, 1000 };
	MediaPlayer alert;

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		CharSequence from = intent.getStringExtra("Name");
		CharSequence message = intent.getStringExtra("Description");
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(), 0);

		notification = new Notification(R.drawable.rem, "Notification",
				System.currentTimeMillis());
		notification.vibrate = vibration;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(context, from, message, contentIntent);
		mNotificationManager.notify(
				Integer.parseInt(intent.getExtras().get("NotifyCount")
						.toString()), notification);
		
		Thread kyra = new Thread(){
    		public void run(){
    			try{
    				alert = MediaPlayer.create(context, R.raw.alert);
    				alert.start();
    				sleep(3000);
    			}catch(InterruptedException ee){
    				
    				ee.printStackTrace();
    				
    			}finally{
    				alert.release();
    			}
    			
    		}
    	 };
    	 kyra.start();
		
		Toast.makeText(context, "New Notification Received", Toast.LENGTH_LONG)
				.show();
	}
}
