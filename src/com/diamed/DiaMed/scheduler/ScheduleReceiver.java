
package com.diamed.DiaMed.scheduler;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.diamed.DiaMed.R;
import com.diamed.DiaMed.*;


public class ScheduleReceiver extends BroadcastReceiver {

    private static final String DEBUG_TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(DEBUG_TAG, "Recurring alarm; requesting upload service.");
        // start the activity
        
        int nmbr = 0;
        Intent upload = new Intent(context, BackupBloodPressure.class);
        upload.putExtra("progressbar", nmbr);
        context.startActivity(upload);
        
    }

    /**
     * Cancels alarms pointing at AlarmReceiver
     * 
     * @param context A valid context
     */
    public static void cancelRecurringAlarm(Context context) {
        Intent downloader = new Intent(context ,ScheduleReceiver.class);
        PendingIntent recurringDownload = PendingIntent.getBroadcast(context,
                0, downloader, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarms = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        alarms.cancel(recurringDownload);
    }

    /**
     * Sets alarm, to be received by AlarmReceiver at a specific 
     * time, daily.
     * 
     *
     */
    public static void setRecurringAlarm(Context context) {
        Calendar updateTime = Calendar.getInstance();
        updateTime.setTimeZone(TimeZone.getTimeZone("GMT"));
        updateTime.set(Calendar.HOUR_OF_DAY, 11);
        updateTime.set(Calendar.MINUTE, 45);

        Intent downloader = new Intent(context,ScheduleReceiver.class);
        PendingIntent recurringDownload = PendingIntent.getBroadcast(context,
                0, downloader, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarms = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                updateTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                recurringDownload);
    }

}
