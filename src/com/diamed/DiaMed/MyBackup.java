package com.diamed.DiaMed;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

public class MyBackup extends BackupAgentHelper {

	static final String File_Name_Of_Prefrences = "myPrefrences";
	static final String PREFS_BACKUP_KEY = "backup";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		SharedPreferencesBackupHelper helper = 
				new SharedPreferencesBackupHelper(getApplicationContext(), File_Name_Of_Prefrences);

		addHelper(PREFS_BACKUP_KEY, helper);
	}

}
