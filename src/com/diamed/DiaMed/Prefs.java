package com.diamed.DiaMed;




import com.diamed.DiaMed.tutorial.TutorialActivity;
import com.diamed.DiaMed.scheduler.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;


public class Prefs extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	public static final String TAG="THEMES";		// Debugging tag
	private static int id;
	
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        
        // Register a change listener     
        Context context = getApplicationContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.registerOnSharedPreferenceChangeListener(this);
        
        /*getPreferenceManager().setSharedPreferencesName(
                UploadSharedPrefs.PREFS_NAME);*/
        
        
        Intent intent = getIntent();
        id = intent.getIntExtra("intentId", 0);
        
    }

    // Inherited abstract method so it must be implemented.  For now we just output some info.
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {	
        Log.i(TAG, "Preferences changed, key="+key);
        // Restart present Activity (ThemesDemo) to force background theme change for it
        if(key.equals( "pref4")||key.equals( "pref5")||key.equals( "pref6")){
        	//finish();
	        Intent j = new Intent(this, MainMenu.class);
	        startActivity(j);
        }
       
    }
    
    

    
    
    // Static method returns SharedPreference value for pref1 (title bar preferences); default is 
    // true if no value previously stored.
    
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//finish();
        Intent j = new Intent(this, MainMenu.class);
        startActivity(j);
	}

	public static boolean getTitlebarPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("pref1", true);
    }
    
    // Static method returns SharedPreference value for pref2 (job priority preference); default 
    // is 1 if no value previously stored.
    
    public static String getJobPriorityPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString("pref2", "1");
    }
    
    // Static method returns SharedPreference value for pref3 (Username); default is blank
    // if no value previously stored.
    
    public static String getUsernamePref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString("pref3", "");
    }
    
    // Static method returns SharedPreference value for pref4 (background color them); default is blank
    // if no value previously stored.
    
    public static boolean getBackgroundThemePref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("pref4", true);
    }
    //check authentication
    public static boolean getAuthenticationPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("pref5", true);
    }
    //enables device full screen
    public static boolean getScreenPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("pref6", true);
    }
    //user demo
    public static boolean startUserDemoOnStartUp(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("pref7", true);
    }
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		Context context = getApplicationContext();
        if (UploadSharedPrefs.getBackgroundUpdateFlag(getApplicationContext())) {
            ScheduleReceiver.setRecurringAlarm(context);
        } else {
        	ScheduleReceiver.cancelRecurringAlarm(context);
        }
		finish();
	}
	

}


