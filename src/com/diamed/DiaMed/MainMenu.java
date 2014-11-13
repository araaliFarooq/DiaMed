package com.diamed.DiaMed;

//import com.diamed.group.intro.intro;

import java.util.ArrayList;

import com.diamed.DiaMed.tutorial.TutorialActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressWarnings("unused")
public class MainMenu extends Activity implements OnItemClickListener {

	private static final int ID_ADD = 1;
	private static final int ID_ACCEPT = 2;
	private static final int ID_UPLOAD = 3;
	private static final int ID_PIECHART = 1;
	private static final int ID_LINE = 2;
	private static final int ID_BAR = 3;
	static final int DIALOG_ID = 0;
	private static final String TAG = "THEMES"; // Debugging tag
	private static final int launcherIcon = R.drawable.bugroid_small;
	static int idz;
	AlertDialog alertBox2 = null;
	AlertDialog alertBox = null;
	AlertDialog alertDialog = null;
	GridView gridview;
	GridViewAdapter gridviewAdapter;
	ArrayList<Item> data = new ArrayList<Item>();
	private static final int ID_FCBK = 1;
	private static final int ID_BLOG = 2;
	private static QuickActionPopup quickActionPopup;
	private static View v;
	private static Prefs prefs;

	/**
	 * Method onCreate(Bundle savedInstanceState) is called when the activity is
	 * first created.
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prefs = new Prefs();
		// Following options to change the Theme must precede setContentView().
		boolean isLight = prefs.getBackgroundThemePref(getApplicationContext());
		if (isLight) {
			setTheme(android.R.style.Theme_Light);
		} else {
			setTheme(android.R.style.Theme_Black);
		}

		// enables and disables full screen on device
		boolean fullScreen = prefs.getScreenPref(getApplicationContext());

		if (fullScreen) {

			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);

		}

		// if user demo activated
		

			setContentView(R.layout.activity_main);

			QuickActionItem facebook = new QuickActionItem(ID_FCBK, "Facebook",
					getResources().getDrawable(R.drawable.facebook));
			QuickActionItem blogger = new QuickActionItem(ID_BLOG, "Blog",
					getResources().getDrawable(R.drawable.blogger));

			// create QuickActionPopup. Use QuickActionPopup.VERTICAL or
			// QuickActionPopup.HORIZONTAL //param to define orientation
			quickActionPopup = new QuickActionPopup(this,
					QuickActionPopup.VERTICAL);

			// add action items into QuickActionPopup
			quickActionPopup.addActionItem(facebook);
			quickActionPopup.addActionItem(blogger);

			// Set listener for action item clicked
			quickActionPopup
					.setOnActionItemClickListener(new QuickActionPopup.OnActionItemClickListener() {
						@Override
						public void onItemClick(QuickActionPopup source,
								int pos, int actionId) {

							// filtering items by id
							if (actionId == ID_FCBK) {
								launchFacebook(v);
							} else if (actionId == ID_BLOG) {
								launchBlog(v);
							}
						}
					});

			// set dismiss listener
			quickActionPopup
					.setOnDismissListener(new QuickActionPopup.OnDismissListener() {
						@Override
						public void onDismiss() {
							Toast.makeText(getApplicationContext(),
									"Dismissed", Toast.LENGTH_SHORT).show();
						}
					});

			initView(); // Initialize the GUI Components
			fillData(); // Insert The Data
			setDataAdapter();
		

	}

	public void launchFacebook(View view) {
		Uri uriUrl = Uri.parse("http://facebook.com/diamedforum/");
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);
	}

	public void launchBlog(View view) {
		Uri uriUrl = Uri.parse("http://farooqp35h@wordpress.com/");
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);
	}

	// Initialize the GUI Components
	private void initView() {
		gridview = (GridView) findViewById(R.id.gridView);
		gridview.setOnItemClickListener(this);
	}

	// Insert The Data
	private void fillData() {
		data.add(new Item("Diabetes", getResources()
				.getDrawable(R.drawable.bdg)));
		data.add(new Item("Blood Pressure", getResources().getDrawable(
				R.drawable.bdp)));
		data.add(new Item("Related Disease", getResources().getDrawable(
				R.drawable.log)));
		data.add(new Item("View Info", getResources().getDrawable(
				R.drawable.view)));
		data.add(new Item("Hospital", getResources().getDrawable(
				R.drawable.hospital)));
		data.add(new Item("Tools", getResources().getDrawable(R.drawable.tool)));
		data.add(new Item("Reminder", getResources().getDrawable(
				R.drawable.reminder)));
		data.add(new Item("Forums", getResources()
				.getDrawable(R.drawable.forum)));
	}

	// Set the Data Adapter
	private void setDataAdapter() {
		gridviewAdapter = new GridViewAdapter(getApplicationContext(),
				R.layout.item, data);
		gridview.setAdapter(gridviewAdapter);
	}

	@Override
	public void onItemClick(final AdapterView<?> arg0, final View view,
			final int position, final long id) {

		switch (position) {
		case 0:
			Intent i = new Intent(MainMenu.this, DiabetesValue.class);
			MainMenu.this.startActivity(i);
			break;
		case 1:
			Intent j = new Intent(MainMenu.this, presvalue2.class);
			MainMenu.this.startActivity(j);
			break;
		case 2:
			Intent k = new Intent(MainMenu.this, SaveData.class);
			startActivity(k);
			break;
		case 3:
			try {
				showAlertBox();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getApplicationContext(), "No Information Found",
						Toast.LENGTH_LONG).show();

			}
			break;
		case 4:

			LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
			if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
					|| !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				// Build the alert dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("\tLocation Services Not Active");
				builder.setMessage("Please enable Location Services and GPS");
				builder.setIcon(R.drawable.fail);
				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialogInterface, int i) {
								// Show location settings when the user
								// acknowledges the alert dialog
								Intent intent = new Intent(
										Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(intent);
							}
						});
				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialogInterface, int i) {
								// Show location settings when the user
								// acknowledges the alert dialog
								try {
									alertDialog.dismiss();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
				Dialog alertDialog = builder.create();
				alertDialog.setCanceledOnTouchOutside(false);
				alertDialog.show();

			} else {

				Intent hsptal = new Intent(MainMenu.this, Hospitals.class);
				MainMenu.this.startActivity(hsptal);

			}

			break;

		case 5:
			Intent tool = new Intent(MainMenu.this, ControlPanel.class);
			MainMenu.this.startActivity(tool);
			break;
		case 6:
			Intent reminder = new Intent(MainMenu.this, TaskReminder.class);
			MainMenu.this.startActivity(reminder);
			break;
		case 7:
			quickActionPopup.show(view);
			break;
		}

	}

	@Override
	public void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	/**
	 * Method showTask() creates a custom launch dialog popped up by a
	 * long-press on a button.
	 */

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
			int id = 0;
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

	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_ID:
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("\tChoose A Task From Dialog")
					.setCancelable(true)
					.setPositiveButton("Change Pin",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent j = new Intent(MainMenu.this,
											ChangePin.class);
									MainMenu.this.startActivity(j);

								}
							})

					.setNeutralButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									try {
										((DialogInterface) builder).dismiss();

									} catch (Exception ee) {
										Log.i("Builder_excptn", "" + ee);
									}
								}
							})
					.setNegativeButton("Preferences",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									Intent j = new Intent(MainMenu.this,
											Prefs.class);
									MainMenu.this.startActivity(j);
								}
							});
			AlertDialog alert = builder.create();
			dialog = alert;
			break;

		default:

		}
		return dialog;
	}

	private void showAlertBox() {

		alertBox = new AlertDialog.Builder(this).create();
		alertBox.setTitle("\t\t\t\t\t\tView Records");
		alertBox.setMessage("Choose What You Want To View");
		alertBox.setCancelable(true);

		alertBox.onWindowFocusChanged(true);

		final LayoutInflater inflater = LayoutInflater.from(this);
		final View alert_webview = inflater.inflate(R.layout.resultview, null);
		alertBox.setView(alert_webview);

		Button sub = (Button) alert_webview.findViewById(R.id.btndetail);
		Button close = (Button) alert_webview.findViewById(R.id.btngraph);

		close.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				showAlertBox2();

			}
		});

		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				final int vId = v.getId();

				ActionItem addItem = new ActionItem(ID_ADD, "Blood Pressure",
						getResources().getDrawable(R.drawable.bdp));
				ActionItem acceptItem = new ActionItem(ID_ACCEPT, "Diabetes",
						getResources().getDrawable(R.drawable.bdg));
				ActionItem uploadItem = new ActionItem(ID_UPLOAD, "MyFile",
						getResources().getDrawable(R.drawable.log));
				uploadItem.setSticky(true);

				QuickAction mQuickAction = new QuickAction(MainMenu.this);

				// second display
				mQuickAction.addActionItem(addItem);
				mQuickAction.addActionItem(acceptItem);
				mQuickAction.addActionItem(uploadItem);

				mQuickAction
						.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
							@Override
							public void onItemClick(QuickAction quickAction,
									int pos, int actionId) {
								ActionItem actionItem = quickAction
										.getActionItem(pos);

								if (actionId == ID_ADD) {
									Toast.makeText(
											getApplicationContext(),
											"You've Chosen To View Your Blood Pressure Records",
											Toast.LENGTH_SHORT).show();
									Intent i1 = new Intent(MainMenu.this,
											ViewPressure.class);
									alertBox.dismiss();
									startActivity(i1);

								} else if (actionId == ID_ACCEPT) {
									Toast.makeText(
											getApplicationContext(),
											"You've Chosen To View Your Diabetes Records",
											Toast.LENGTH_SHORT).show();
									Intent i1 = new Intent(MainMenu.this,
											ViewDiabetes.class);
									alertBox.dismiss();
									startActivity(i1);

								} else if (actionId == ID_UPLOAD) {
									Toast.makeText(
											getApplicationContext(),
											"You've Chosen To View Your Drug FollowUp ",
											Toast.LENGTH_SHORT).show();
									Intent i1 = new Intent(MainMenu.this,
											ViewOtherDiseases.class);
									alertBox.dismiss();
									startActivity(i1);

								}
							}
						});

				switch (vId) {
				case R.id.btndetail:
					try {

						mQuickAction.show(v);
						mQuickAction
								.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);

					} catch (Exception ee) {
						Log.i("Builder_excptn", "" + ee);
					}
					break;
				default:
					break;
				}

			}
		});
		alertBox.show();

	}

	private void showAlertBox2() {
		// AlertDialog alertBox2 = null;
		alertBox2 = new AlertDialog.Builder(this).create();
		alertBox2.setTitle("\t\t\t\t\t\tChoose Graph");
		alertBox2.setMessage("Select Graph You Want To View");
		alertBox2.setCancelable(true);

		final LayoutInflater inflater = LayoutInflater.from(this);
		final View alert_webview = inflater.inflate(R.layout.selectgraph, null);
		alertBox2.setView(alert_webview);

		final Button submit = (Button) alert_webview.findViewById(R.id.btnsub);

		RadioGroup radiogrp = (RadioGroup) alert_webview
				.findViewById(R.id.radioGrp);

		radiogrp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub

				switch (checkedId) {
				case R.id.option1:
					submit.setEnabled(true);
					idz = 1;
					break;

				case R.id.option2:
					submit.setEnabled(true);
					idz = 2;
					break;

				}

			}
		});

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				final int vId = v.getId();
				switch (vId) {
				case R.id.btnsub:
					if (idz == 1) {

						ActionItem line = new ActionItem(ID_LINE, "Line Graph",
								getResources()
										.getDrawable(R.drawable.linegraph));
						ActionItem piechart = new ActionItem(ID_PIECHART,
								" Piechart", getResources().getDrawable(
										R.drawable.piechat));
						ActionItem bar = new ActionItem(ID_BAR, " Bar Graph",
								getResources().getDrawable(R.drawable.bargraph));
						// use setSticky(true) to disable QuickAction dialog
						// being dismissed after an item is clicked

						final QuickAction mQuickAction = new QuickAction(
								MainMenu.this);// second display..
						final QuickAction mQuickAction1 = new QuickAction(
								MainMenu.this);// ;first display..

						// first display
						mQuickAction1.addActionItem(piechart);
						mQuickAction1.addActionItem(line);
						mQuickAction1.addActionItem(bar);

						// setup the action item click listener
						mQuickAction1
								.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
									@Override
									public void onItemClick(
											QuickAction quickAction, int pos,
											int actionId) {
										ActionItem actionItem = quickAction
												.getActionItem(pos);

										if (actionId == ID_PIECHART) {

											Toast.makeText(
													getApplicationContext(),
													"You've Chosen To View Records On A PieChart Graph",
													Toast.LENGTH_SHORT).show();

											pressurePieChart myClass = new pressurePieChart();
											Intent intent = null;
											alertBox2.dismiss();
											intent = myClass
													.execute(MainMenu.this);
											startActivity(intent);

										} else if (actionId == ID_LINE) {
											Toast.makeText(
													getApplicationContext(),
													"You've Chosen To View Records On A Line Graph",
													Toast.LENGTH_SHORT).show();
											pressureLineChart myClass = new pressureLineChart();
											Intent i2 = null;
											alertBox2.dismiss();
											i2 = myClass.execute(MainMenu.this);
											startActivity(i2);

										} else if (actionId == ID_BAR) {
											Toast.makeText(
													getApplicationContext(),
													"You've Chosen To View Records On A Bar Graph",
													Toast.LENGTH_SHORT).show();

											PressureBarChart myClass = new PressureBarChart();
											Intent i1 = null;
											alertBox2.dismiss();
											i1 = myClass.execute(MainMenu.this);
											startActivity(i1);

										}
									}
								});

						mQuickAction
								.setOnDismissListener(new QuickAction.OnDismissListener() {
									@Override
									public void onDismiss() {
										Toast.makeText(getApplicationContext(),
												"Options dismissed",
												Toast.LENGTH_SHORT).show();
									}
								});

						// QuickAction mQuickAction1 = new
						// QuickAction(DisplayItems.this);

						// TODO Auto-generated method stub
						mQuickAction1.show(v);
						mQuickAction1
								.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);

						Toast.makeText(getApplicationContext(),
								"Blood pressure", Toast.LENGTH_SHORT).show();

					} else if (idz == 2) {

						ActionItem line = new ActionItem(ID_LINE, "Line Graph",
								getResources()
										.getDrawable(R.drawable.linegraph));
						ActionItem piechart = new ActionItem(ID_PIECHART,
								" Piechart", getResources().getDrawable(
										R.drawable.piechat));
						ActionItem bar = new ActionItem(ID_BAR, " Bar Graph",
								getResources().getDrawable(R.drawable.bargraph));
						// use setSticky(true) to disable QuickAction dialog
						// being dismissed after an item is clicked

						final QuickAction mQuickAction = new QuickAction(
								MainMenu.this);// second display..
						final QuickAction mQuickAction1 = new QuickAction(
								MainMenu.this);// ;first display..

						// first display
						mQuickAction1.addActionItem(piechart);
						mQuickAction1.addActionItem(line);
						mQuickAction1.addActionItem(bar);

						// setup the action item click listener
						mQuickAction1
								.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
									@Override
									public void onItemClick(
											QuickAction quickAction, int pos,
											int actionId) {
										ActionItem actionItem = quickAction
												.getActionItem(pos);

										if (actionId == ID_PIECHART) {

											Toast.makeText(
													getApplicationContext(),
													"You've Chosen To View Records On A PieCahrt Graph",
													Toast.LENGTH_SHORT).show();
											DiabetesPieChart myClass = new DiabetesPieChart();
											Intent intent = null;

											alertBox2.dismiss();
											intent = myClass
													.execute(MainMenu.this);
											startActivity(intent);

										} else if (actionId == ID_LINE) {

											// diabetes linechart

											Toast.makeText(
													getApplicationContext(),
													"You've Chosen To View Records On A Line Graph",
													Toast.LENGTH_SHORT).show();

											DiabetesLineChart myClass = new DiabetesLineChart();
											Intent intent = null;

											alertBox2.dismiss();
											intent = myClass
													.execute(MainMenu.this);
											startActivity(intent);

										} else if (actionId == ID_BAR) {
											Toast.makeText(
													getApplicationContext(),
													"You've Chosen To View Records On A Daibetes Bar Graph",
													Toast.LENGTH_SHORT).show();

											DiabetesBarChart myClass = new DiabetesBarChart();
											Intent intent = null;

											alertBox2.dismiss();
											intent = myClass
													.execute(MainMenu.this);
											startActivity(intent);

										}
									}
								});

						mQuickAction
								.setOnDismissListener(new QuickAction.OnDismissListener() {
									@Override
									public void onDismiss() {
										Toast.makeText(getApplicationContext(),
												"Options dismissed",
												Toast.LENGTH_SHORT).show();
									}
								});

						// QuickAction mQuickAction1 = new
						// QuickAction(DisplayItems.this);

						// TODO Auto-generated method stub
						mQuickAction1.show(v);
						mQuickAction1
								.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);

						Toast.makeText(getApplicationContext(), "Diabetes",
								Toast.LENGTH_SHORT).show();
					}

					break;
				default:
					break;
				}

			}
		});
		alertBox2.show();

	}

}
