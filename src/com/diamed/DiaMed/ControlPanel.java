package com.diamed.DiaMed;

import com.diamed.DiaMed.tutorial.TutorialActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ControlPanel extends Activity {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controlpanel);

		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.list);

		// Defined Array values to show in ListView
		String[] values = new String[] { "Change Pin", "Register App",
				"Demo Tutorial", "Contact Doctor", "Register Doctor",
				"System Preferences", "Create Data Backup",
				"Restore from Backup"

		};

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:

					Intent j = new Intent(ControlPanel.this, ChangePin.class);
					ControlPanel.this.startActivity(j);

					break;
				case 1:
					Intent x = new Intent(ControlPanel.this,
							RegisterApp.class);
					ControlPanel.this.startActivity(x);

					break;
				case 2:
					Intent tutorial = new Intent(ControlPanel.this,
							TutorialActivity.class);
					ControlPanel.this.startActivity(tutorial);
					break;

				case 3:

					break;
				case 4:

					Intent y = new Intent(ControlPanel.this,
							doctorsRegister.class);
					ControlPanel.this.startActivity(y);

					break;
				case 5:

					int id = 0;
					Intent t = new Intent(ControlPanel.this, Prefs.class);
					t.putExtra("intentId", id);
					ControlPanel.this.startActivity(t);

					break;
				case 6:
					Intent zt = new Intent(ControlPanel.this, Backup.class);
					ControlPanel.this.startActivity(zt);
					
					break;

				case 7:
					Intent z = new Intent(ControlPanel.this,RestoreBackup.class);
					ControlPanel.this.startActivity(z);
					
					break;
				default:
					
					Intent zi = new Intent(ControlPanel.this,MainMenu.class);
					ControlPanel.this.startActivity(zi);
					break;
				}

			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent home = new Intent(ControlPanel.this,MainMenu.class);
		ControlPanel.this.startActivity(home);
	}

	
}