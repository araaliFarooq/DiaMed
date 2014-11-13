package com.diamed.DiaMed;

import java.util.Calendar;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ActivityBeforePressureReading extends Activity {
	private static final int ID_ADD2 = 1;
	private static final int ID_ACCEPT2 = 2;
	private static final int ID_UPLOAD2 = 3;
	static int idy = 0;
	private static DatabaseManipulator dh;
	static String activity;
	static final int DIALOG_ID = 0;
	Calendar ci = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radiogrp2);
		RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGrp2);
		final Button submit = (Button) findViewById(R.id.btnsub);
		Button close = (Button) findViewById(R.id.btnclose);

		// close button...
		close.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent back = new Intent(ActivityBeforePressureReading.this, presvalue2.class);
				ActivityBeforePressureReading.this.startActivity(back);
			}
		});

		Intent x = getIntent();
		final String value3 = x.getStringExtra("key1");
		// final float value41 = x.getFloatExtra("key2",0);
		final String value4 = x.getStringExtra("key2");
		final float value41 = x.getFloatExtra("key3", 0);
		final float value42 = x.getFloatExtra("key4", 0);

		// System Time picker
		final String date = "" + ci.get(Calendar.DAY_OF_MONTH) + "/"
				+ (ci.get(Calendar.MONTH) + 1) + "/"
				+ ci.get(Calendar.YEAR) ;
		
		final String Time = "" + ci.get(Calendar.HOUR)
				+ ":" + ci.get(Calendar.MINUTE) + ":" + ci.get(Calendar.SECOND);

		Log.i("TAG_sys", "" + value41);
		Log.i("TAG_dia", "" + value42);

		ActionItem addItem = new ActionItem(ID_ADD2, "Enter + Save",
				getResources().getDrawable(R.drawable.ic_add));
		
		ActionItem acceptItem = new ActionItem(ID_ACCEPT2, "Enter",
				getResources().getDrawable(R.drawable.ic_accept));
		
		ActionItem uploadItem = new ActionItem(ID_UPLOAD2, "Save",
				getResources().getDrawable(R.drawable.ic_up));

		// use setSticky(true) to disable QuickAction dialog being dismissed
		// after an item is clicked
		uploadItem.setSticky(true);

		final QuickAction mQuickAction = new QuickAction(ActivityBeforePressureReading.this);

		mQuickAction.addActionItem(addItem);
		mQuickAction.addActionItem(acceptItem);
		mQuickAction.addActionItem(uploadItem);

		// setup the action item click listener
		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@SuppressWarnings({ "unused", "static-access" })
					@Override
					public void onItemClick(QuickAction quickAction, int pos,
							int actionId) {
						ActionItem actionItem = quickAction.getActionItem(pos);

						if (actionId == ID_ADD2) {

							Toast.makeText(getApplicationContext(),
									"Data Will Also Be Saved",
									Toast.LENGTH_SHORT).show();
							try {
								// save to database
								ActivityBeforePressureReading.this.dh = new DatabaseManipulator(
										ActivityBeforePressureReading.this);
								ActivityBeforePressureReading.this.dh.insert4(date, Time, activity,
										value3, value4);
								// start new activity when done saving
								Intent i = new Intent(ActivityBeforePressureReading.this,
										RecomendationForPressure.class);
								i.putExtra("key5", value41);
								i.putExtra("key6", value42);
								i.putExtra("key7", idy);
								ActivityBeforePressureReading.this.startActivity(i);
							} catch (Exception ee) {
								Log.i("Excptn_rdgrp2", "" + ee);
							}
							/*
							 * Thread minawa = new Thread(){ public void run(){
							 * try{ sleep(1000); }catch(InterruptedException
							 * ee){ ee.printStackTrace(); }finally{ Intent
							 * openAgainActivity = new
							 * Intent(radiogrp2.this,recomend.class);
							 * radiogrp2.this.startActivity(openAgainActivity);
							 * }
							 * 
							 * } };
							 */
						} else if (actionId == ID_ACCEPT2) {

							Toast.makeText(getApplicationContext(),
									"Data Will Not Be Saved",
									Toast.LENGTH_SHORT).show();
							Intent i = new Intent(ActivityBeforePressureReading.this,
									RecomendationForPressure.class);
							i.putExtra("key5", value41);
							i.putExtra("key6", value42);
							i.putExtra("key7", idy);
							ActivityBeforePressureReading.this.startActivity(i);

						} else if (actionId == ID_UPLOAD2) {

							Toast.makeText(getApplicationContext(),
									"Data Will Only Be Saved",
									Toast.LENGTH_SHORT).show();
							// save to database
							ActivityBeforePressureReading.this.dh = new DatabaseManipulator(
									ActivityBeforePressureReading.this);
							ActivityBeforePressureReading.this.dh.insert4(date,Time, activity,
									value3, value4);
							showDialog(DIALOG_ID);
						}
					}
				});

		mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
			@Override
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Options dismissed",
						Toast.LENGTH_SHORT).show();
			}
		});

		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub

				switch (checkedId) {
				case R.id.option1:
					submit.setEnabled(true);
					activity = "Before Exercise";
					idy = 1;
					break;
				case R.id.option2:
					submit.setEnabled(true);
					activity = "After Exercise";
					idy = 2;
					break;
				case R.id.option3:
					submit.setEnabled(true);
					activity = "Bed Time";
					idy = 3;
					break;
				case R.id.option4:
					submit.setEnabled(true);
					activity = "After Bed";
					idy = 4;
					break;
				}
				Intent y = new Intent(ActivityBeforePressureReading.this, RecomendationForPressure.class);
				y.putExtra("key3", idy);

			}
		});
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println(idy);
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
					"Information saved successfully ! Want To Carry Out Another Task?")
					.setCancelable(false)
					.setPositiveButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent d = new Intent(ActivityBeforePressureReading.this,
											MainMenu.class);
									ActivityBeforePressureReading.this.startActivity(d);
								}
							})
					.setNegativeButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			dialog = alert;
			break;

		default:

		}
		return dialog;
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
			// Actions for preferences page
			Intent j = new Intent(this, Prefs.class);
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

}
