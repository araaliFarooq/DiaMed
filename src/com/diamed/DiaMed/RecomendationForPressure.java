package com.diamed.DiaMed;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecomendationForPressure extends Activity {

	static float systolic;
	static final int DIALOG_ID = 0;
	static float diastolic;
	static int radioId;
	static View alert_webview = null;
	static Button finish;
	static Button tryButton;
	static Button listen;
	static Button listen2;
	static String recommendation;
	static Button exit;
	private static int MY_DATA_CHECK_CODE = 0;
	private static DatabaseManipulator dbase;
	static String[] stg5;
	static String[] stg6;
	String s1;
	String s2;
	static List<String[]> names2 = null;
	private static String pusername;
	private static String dcontact;
	private static int critical;
	private static int intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		systolic = i.getFloatExtra("key5", 0);
		diastolic = i.getFloatExtra("key6", 0);
		radioId = i.getIntExtra("key7", 0);

		Log.i("systolic", "" + systolic);
		Log.i("diastolic", "" + diastolic);
		Log.i("radioId", "" + radioId);

		dbase = new DatabaseManipulator(this);
		try {
			names2 = dbase.selectDocDetails();

			stg5 = new String[names2.size()];
			stg6 = new String[names2.size()];

			int x = 0;

			for (String[] name : names2) {
				s1 = name[1];
				s2 = name[5];

				stg5[x] = s1;
				stg6[x] = s2;
				x++;

			}

			pusername = stg5[0];

			dcontact = stg6[0];

			Log.i("username", pusername);
			Log.i("contact", dcontact);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			Log.i("docs details", e.toString());
		}

		showAlertBox();

	}

	private void showAlertBox() {
		AlertDialog alertBox = null;
		alertBox = new AlertDialog.Builder(this).create();
		alertBox.setTitle("\t\tRecommendation");

		alertBox.setCancelable(false);

		final LayoutInflater inflater = LayoutInflater.from(this);

		if ((systolic >= 50 && systolic <= 70)
				&& (diastolic <= 50 && diastolic >= 35) && radioId == 1) {

			View alert_webview = inflater.inflate(R.layout.recommend6, null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend6);
			critical = 1;

		} else if ((systolic >= 50 && systolic <= 70)
				&& (diastolic <= 50 && diastolic >= 35) && radioId == 2) {
			View alert_webview = inflater.inflate(R.layout.recommend6, null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend6);
			critical = 1;

		} else if ((systolic >= 50 && systolic <= 70)
				&& (diastolic <= 50 && diastolic >= 35) && radioId == 3) {
			View alert_webview = inflater.inflate(R.layout.recommend6, null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend6);
			critical = 1;

		} else if ((systolic >= 50 && systolic <= 70)
				&& (diastolic <= 50 && diastolic >= 35) && radioId == 4) {
			View alert_webview = inflater.inflate(R.layout.recommend6, null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend6);
			critical = 1;

		} else if ((systolic >= 135 && systolic <= 230)
				&& (diastolic <= 165 && diastolic >= 100) && radioId == 1) {
			View alert_webview = inflater.inflate(R.layout.recommend1, null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend1);
			critical = 1;

		} else if ((systolic >= 135 && systolic <= 230)
				&& (diastolic <= 165 && diastolic >= 100) && radioId == 2) {
			final View alert_webview = inflater.inflate(R.layout.recommend1,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend1);
			critical = 1;

		} else if ((systolic >= 135 && systolic <= 230)
				&& (diastolic <= 165 && diastolic >= 100) && radioId == 3) {
			final View alert_webview = inflater.inflate(R.layout.recommend1,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend1);
			critical = 1;

		} else if ((systolic >= 135 && systolic <= 230)
				&& (diastolic <= 165 && diastolic >= 100) && radioId == 4) {

			final View alert_webview = inflater.inflate(R.layout.recommend1,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			critical = 1;
			recommendation = getString(R.string.recommend1);

		} else if ((systolic >= 100 && systolic <= 165)
				&& (diastolic <= 150 && diastolic >= 90) && radioId == 1) {
			final View alert_webview = inflater.inflate(R.layout.recommend2,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend2);
			critical = 1;

		} else if ((systolic >= 100 && systolic <= 165)
				&& (diastolic <= 150 && diastolic >= 90) && radioId == 2) {
			final View alert_webview = inflater.inflate(R.layout.recommend2,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend2);
			critical = 1;

		} else if ((systolic >= 100 && systolic <= 165)
				&& (diastolic <= 150 && diastolic >= 90) && radioId == 3) {
			final View alert_webview = inflater.inflate(R.layout.recommend2,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend2);
			critical = 1;

		} else if ((systolic >= 100 && systolic <= 165)
				&& (diastolic <= 150 && diastolic >= 90) && radioId == 4) {

			final View alert_webview = inflater.inflate(R.layout.recommend2,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			critical = 1;
			recommendation = getString(R.string.recommend2);

		} else if ((systolic >= 90) && (systolic <= 150) && (diastolic <= 135)
				&& (diastolic >= 85) && radioId == 1) {
			final View alert_webview = inflater.inflate(R.layout.recommend3,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend3);

		} else if ((systolic >= 90) && (systolic <= 150) && (diastolic <= 135)
				&& (diastolic >= 85) && radioId == 2) {
			final View alert_webview = inflater.inflate(R.layout.recommend4,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend4);

		} else if ((systolic >= 90) && (systolic <= 150) && (diastolic <= 135)
				&& (diastolic >= 85) && radioId == 3) {
			final View alert_webview = inflater.inflate(R.layout.recommend3,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend3);

		} else if ((systolic >= 90) && (systolic <= 150) && (diastolic <= 135)
				&& (diastolic >= 85) && radioId == 4) {
			final View alert_webview = inflater.inflate(R.layout.recommend3,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend3);

		} else if ((systolic >= 85 && systolic <= 135)
				&& (diastolic <= 95 && diastolic >= 65) && radioId == 1) {
			final View alert_webview = inflater.inflate(R.layout.recommend4,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend4);

		} else if ((systolic >= 85 && systolic <= 135)
				&& (diastolic <= 95 && diastolic >= 65) && radioId == 2) {
			final View alert_webview = inflater.inflate(R.layout.recommend5,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend5);
			critical = 1;

		} else if ((systolic >= 85 && systolic <= 135)
				&& (diastolic <= 95 && diastolic >= 65) && radioId == 3) {
			final View alert_webview = inflater.inflate(R.layout.recommend4,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend4);

		} else if ((systolic >= 85 && systolic <= 135)
				&& (diastolic <= 95 && diastolic >= 65) && radioId == 4) {
			final View alert_webview = inflater.inflate(R.layout.recommend4,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend4);

		} else if ((systolic >= 65 && systolic <= 95)
				&& (diastolic <= 70 && diastolic >= 50) && radioId == 1) {
			final View alert_webview = inflater.inflate(R.layout.recommend5,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend5);
			critical = 1;

		} else if ((systolic >= 65 && systolic <= 95)
				&& (diastolic <= 70 && diastolic >= 50) && radioId == 2) {
			final View alert_webview = inflater.inflate(R.layout.recommend6,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend6);
			critical = 1;

		} else if ((systolic >= 65 && systolic <= 95)
				&& (diastolic <= 70 && diastolic >= 50) && radioId == 3) {
			final View alert_webview = inflater.inflate(R.layout.recommend5,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend5);
			critical = 1;

		} else if ((systolic >= 65 && systolic <= 95)
				&& (diastolic <= 70 && diastolic >= 50) && radioId == 4) {
			final View alert_webview = inflater.inflate(R.layout.recommend6,
					null);
			alertBox.setView(alert_webview);
			finish = (Button) alert_webview.findViewById(R.id.back1);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			recommendation = getString(R.string.recommend6);
			critical = 1;

		} else {

			Toast.makeText(getApplicationContext(), "Invalid Entry",
					Toast.LENGTH_LONG).show();
			alertBox.setMessage(systolic + "-" + diastolic);
			final View alert_webview = inflater.inflate(R.layout.overrange,
					null);
			alertBox.setView(alert_webview);
			tryButton = (Button) alert_webview.findViewById(R.id.tryButton);
			listen = (Button) alert_webview.findViewById(R.id.listen);
			finish = (Button) alert_webview.findViewById(R.id.exit);

			recommendation = getString(R.string.overrange);

			try {
				// Activity alert_webview = null;

				tryButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						Intent f = new Intent(RecomendationForPressure.this,
								presvalue2.class);
						RecomendationForPressure.this.startActivity(f);

					}
				});
			} catch (Exception msg) {

				Log.i("tryButton", "" + msg);
			}

		}

		// final View alert_webview = inflater.inflate(R.layout.presvalue,
		// null);

		// alertBox.setView(alert_webview);

		try {
			// Activity alert_webview = null;

			finish.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					intent = 1;
					if (critical == 1) {
						showAlertDialog(
								RecomendationForPressure.this,
								"\t\t\tMESSAGE ALERT",
								"Critical Reading Taken, Allow System To Alert Doctor",
								false);

					} else {
						Toast.makeText(
								getApplicationContext(),
								"If Symptoms Persist...  Seek Qualified Medical Advice",
								Toast.LENGTH_LONG).show();
						Intent finisher = new Intent(
								RecomendationForPressure.this, MainMenu.class);
						RecomendationForPressure.this.startActivity(finisher);
					}
				}
			});
		} catch (Exception msg) {

			Log.i("finish", "" + msg);
		}

		try {
			// Activity alert_webview = null;

			listen.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					intent = 2;
					if (critical == 1) {
						showAlertDialog(
								RecomendationForPressure.this,
								"\t\t\tMESSAGE ALERT",
								"Critical Reading Taken, Allow System \t\t\t\t\tTo Alert Doctor",
								false);

					} else {
						Intent i = new Intent(RecomendationForPressure.this,
								presSpeak.class);
						i.putExtra("rec", recommendation);
						i.putExtra("sys", systolic);
						i.putExtra("dia", diastolic);
						i.putExtra("radio", radioId);
						RecomendationForPressure.this.startActivity(i);
					}

				}
			});
		} catch (Exception msg) {

			Log.i("listen", "" + msg);
		}

		alertBox.show();

	}

	private void sendSMS(String phoneNumber, String message) {
		String SENT = "ALERT_SENT";
		String DELIVERED = "ALERT_DELIVERED";

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
				SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
				new Intent(DELIVERED), 0);

		// ---when the SMS has been sent---
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {

				switch (getResultCode()) {

				case Activity.RESULT_OK:

					Toast.makeText(getBaseContext(), "SMS sent",
							Toast.LENGTH_SHORT).show();
					break;

				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:

					Toast.makeText(getBaseContext(), "Generic failure",
							Toast.LENGTH_SHORT).show();
					break;

				case SmsManager.RESULT_ERROR_NO_SERVICE:

					Toast.makeText(getBaseContext(), "No service",
							Toast.LENGTH_SHORT).show();
					break;

				case SmsManager.RESULT_ERROR_NULL_PDU:

					Toast.makeText(getBaseContext(), "Null PDU",
							Toast.LENGTH_SHORT).show();
					break;

				case SmsManager.RESULT_ERROR_RADIO_OFF:

					Toast.makeText(getBaseContext(), "Radio off",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SENT));

		// ---when the SMS has been delivered---
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {

				switch (getResultCode()) {

				case Activity.RESULT_OK:

					Toast.makeText(getBaseContext(), "SMS delivered",
							Toast.LENGTH_SHORT).show();
					break;

				case Activity.RESULT_CANCELED:

					Toast.makeText(getBaseContext(), "SMS not delivered",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		int groupId = 0;
		int menuItemOrder = Menu.NONE;
		// Create menu ids for the event handler to reference
		int menuItemId1 = Menu.FIRST;
		int menuItemId2 = Menu.FIRST + 1;
		int menuItemId3 = Menu.FIRST + 2;
		int menuItemId4 = Menu.FIRST + 3;
		// Create menu text
		int menuItemText1 = R.string.quit;
		int menuItemText2 = R.string.help_title;
		int menuItemText3 = R.string.settings_title;
		int menuItemText4 = R.string.about_title;
		// Add the items to the menu
		MenuItem menuItem1 = menu.add(groupId, menuItemId1, menuItemOrder,
				menuItemText1).setIcon(R.drawable.ic_menu_close_clear_cancel);
		MenuItem menuItem2 = menu.add(groupId, menuItemId2, menuItemOrder,
				menuItemText2).setIcon(R.drawable.ic_menu_help);
		MenuItem menuItem3 = menu.add(groupId, menuItemId3, menuItemOrder,
				menuItemText3).setIcon(R.drawable.ic_menu_preferences);
		MenuItem menuItem4 = menu.add(groupId, menuItemId4, menuItemOrder,
				menuItemText4).setIcon(R.drawable.ic_menu_info_details);
		return true;
	}

	/** Handle events from the popup options menu above. */

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		// Actions for exit button
		case (Menu.FIRST):
			finish();
			return true;
		case (Menu.FIRST + 1):
			// Actions for help page
			Intent i = new Intent(this, Help.class);
			startActivity(i);
			return true;
		case (Menu.FIRST + 2):
			int id = 0;
			// Actions for preferences page
			Intent j = new Intent(this, Prefs.class);
			j.putExtra("intentId", id);
			startActivity(j);
			return true;
		case (Menu.FIRST + 3):
			// Actions for about page
			Intent k = new Intent(this, About.class);
			startActivity(k);
			return true;
		}
		return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		final AlertDialog alertDialog = new AlertDialog.Builder(context)
				.create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button

		alertDialog.setButton("PROCEED", new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, final int which) {

				final String msg = "Critical readings, Pressure Values: "
						+ systolic + "  " + diastolic + " " + "from "
						+ pusername;
				try {
					sendSMS(dcontact, msg);
				} catch (final Exception e) {
					// TODO Auto-generated catch block
					Log.i("send-sms", e.toString());
					Toast.makeText(
							getApplicationContext(),
							"Message Not Sent.No Doctor Found In System.Register Doctor First Then Try Again",
							Toast.LENGTH_LONG).show();
				}
				alertDialog.dismiss();
				if (intent == 1) {
					final Intent finisher = new Intent(
							RecomendationForPressure.this, MainMenu.class);
					RecomendationForPressure.this.startActivity(finisher);

				} else if (intent == 2) {
					final Intent i = new Intent(RecomendationForPressure.this,
							presSpeak.class);
					i.putExtra("rec", recommendation);
					i.putExtra("sys", systolic);
					i.putExtra("dia", diastolic);
					i.putExtra("radio", radioId);
					RecomendationForPressure.this.startActivity(i);
				}
			}
		});

		alertDialog.setButton2("CANCEL", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();
				if (intent == 1) {
					Intent finisher = new Intent(RecomendationForPressure.this,
							MainMenu.class);
					RecomendationForPressure.this.startActivity(finisher);

				} else if (intent == 2) {
					Intent i = new Intent(RecomendationForPressure.this,
							presSpeak.class);
					i.putExtra("rec", recommendation);
					i.putExtra("sys", systolic);
					i.putExtra("dia", diastolic);
					i.putExtra("radio", radioId);
					RecomendationForPressure.this.startActivity(i);
				}
			}

		});

		// Showing Alert Message
		alertDialog.show();
	}
}
