package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ViewDiabetes extends ListActivity {
	TextView selection;
	public int idToModify;
	DatabaseManipulator dm;
	static Button options;
	static int item;
	String fontPath = "fonts/MyriadPro-BoldCond.otf";
	static Typeface tf;
	static String postn;
	Button del;
	Button deleteall;
	Button cancel;
	Button edit;
	static final int DIALOG_ID = 0;
	static int identifier;
	static int rowId;
	static String nameId;
	String tablename;
	MessageInRow Detail;
	AlertDialog alertBox = null;
	Boolean isInternetPresent = false;
	ConnectionDetector connection;

	ListView msgList;
	ArrayList<MessageInRow> details;
	AdapterView.AdapterContextMenuInfo info;

	List<String> list = new ArrayList<String>();
	List<String[]> list3 = new ArrayList<String[]>();
	List<String[]> tableValues = null;
	String[] stg1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.listview);
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("Listview", e.toString());
		}
		
		connection = new ConnectionDetector(getApplicationContext());
		
		msgList = (ListView) findViewById(R.id.list);

		// font
		tf = Typeface.createFromAsset(getAssets(), fontPath);

		details = new ArrayList<MessageInRow>();

		dm = new DatabaseManipulator(this);

		registerForContextMenu(msgList);

		// final int diabeteId = dm.fetchId2();

		msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub

				msgList.setBackgroundColor(color.background_dark);
				options.setEnabled(true);
				postn = list.get(position);
				rowId = Integer.parseInt(postn);
				Log.i("Data_postn", "" + rowId);

			}
		});

		options = (Button) findViewById(R.id.opt);
		options.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				showAlertBox();

			}
		});

		tableValues = dm.selectAll3();

		stg1 = new String[tableValues.size()];

		int x = 0;
		String stg;

		for (String[] name : tableValues) {

			Detail = new MessageInRow();

			nameId = name[0];
			list.add(name[0]);

			// Detail.setIcon(R.drawable.more);
			Detail.setName(name[4]);
			Detail.setDesc(name[3]);
			Detail.setTime(name[1] + " " + name[2]);
			details.add(Detail);
			x++;

		}

		msgList.setAdapter(new customAdapter2(details, this));
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		selection.setText(stg1[position]);

		options.setEnabled(true);

		postn = list.get(position);
		rowId = Integer.parseInt(postn);

		Log.i("Data_postn", "" + rowId);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Thread minawa = new Thread(){
    		public void run(){
    			try{
    				sleep(18000);
    			}catch(InterruptedException ee){
    				ee.printStackTrace();
    			}finally{
    				finish();
    			}
    			
    		}
    	 };
    	 minawa.start();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		Intent con = new Intent(ViewDiabetes.this, MainMenu.class);
		ViewDiabetes.this.startActivity(con);

	}

	private void showAlertBox() {

		alertBox = new AlertDialog.Builder(this).create();
		alertBox.setTitle("Update  Options");
		alertBox.setCancelable(false);

		final LayoutInflater inflater = LayoutInflater.from(this);
		final View alert_webview = inflater.inflate(R.layout.deleteoptions,
				null);
		alertBox.setView(alert_webview);

		edit = (Button) alert_webview.findViewById(R.id.button0);
		del = (Button) alert_webview.findViewById(R.id.button1);
		deleteall = (Button) alert_webview.findViewById(R.id.button2);

		cancel = (Button) alert_webview.findViewById(R.id.button3);
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent con = new Intent(ViewDiabetes.this, ViewDiabetes.class);
				ViewDiabetes.this.startActivity(con);

			}
		});

		del.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				isInternetPresent = connection.isConnectingToInternet();

				if (isInternetPresent) {

					showDialog(DIALOG_ID);

				} else {

					showAlertDialog(ViewDiabetes.this, "Internet Connection",
							"No Internet Connection", false);

				}
			}
		});

		deleteall.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				Toast.makeText(getApplicationContext(), "Confirm First",
						Toast.LENGTH_LONG).show();
				Intent con = new Intent(ViewDiabetes.this, confirm.class);
				alertBox.dismiss();
				ViewDiabetes.this.startActivity(con);
			}
		});

		edit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent x = new Intent(ViewDiabetes.this, editDiabetes.class);
				x.putExtra("key1", rowId);
				Log.i("Sending", "" + rowId);
				alertBox.dismiss();
				ViewDiabetes.this.startActivity(x);

			}
		});

		alertBox.show();

	}

	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("\t\tConfirm Deletion")
					.setCancelable(false)
					.setPositiveButton("Confirm",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									tablename = "diabetes";
									dm.deleteItem3(rowId);

									Intent i = new Intent(ViewDiabetes.this,
											deleteFromServer.class);
									alertBox.dismiss();
									i.putExtra("pid", rowId);
									i.putExtra("tablename", tablename);
									startActivity(i);

								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
								}
							});
			AlertDialog alert = builder.create();
			dialog = alert;
			break;

		default:

		}
		return dialog;
	}

	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
