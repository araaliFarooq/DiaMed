package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.List;



import android.R.color;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewOtherDiseases extends ListActivity {
	TextView selection;
	public int idToModify;
	DatabaseManipulator dm;
	static Button options;
	String fontPath = "fonts/MyriadPro-BoldCond.otf";
	static Typeface tf;
	String postn;
	Button del;
	Button deleteall;
	Button cancel;
	Button edit;
	static final int DIALOG_ID = 0;
	static int identifier;
	static int rowId;
	MessageInRow Detail;

	ListView msgList;
	
	ArrayList<MessageInRow> details;
	Boolean isInternetPresent = false;
	ConnectionDetector connection;
	AdapterView.AdapterContextMenuInfo info;

	List<String> list = new ArrayList<String>();
	List<String[]> names2 = null;
	String[] stg1;
	protected AlertDialog alertBox;

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

		details = new ArrayList<MessageInRow>();

		// font style.
		tf = Typeface.createFromAsset(getAssets(), fontPath);

		dm = new DatabaseManipulator(this);

		registerForContextMenu(msgList);

		// final int pressureId = dm.fetchId3();

		options = (Button) findViewById(R.id.opt);
		options.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				showAlertBox();

			}
		});

		names2 = dm.selectAll();

		stg1 = new String[names2.size()];

		int x = 0;
		String stg;

		for (String[] name : names2) {

			Detail = new MessageInRow();

			//stg = name[1] + " - " + name[2] + " - " + name[3] + " - " + name[4];

			list.add(name[0]);
			// Detail.setIcon(R.drawable.more);
		//	Detail.s
			Detail.setName(name[2]);
			Detail.setSub(name[3]);
			Detail.setDesc(name[4]);
			Detail.setDuration(name[5]);
			Detail.setStartDate(name[6]);
			Detail.setTime(name[1]);
			
			details.add(Detail);
			x++;
		}

		msgList.setAdapter(new customAdapter3(details, this));

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);

		info = (AdapterContextMenuInfo) menuInfo;

		menu.setHeaderTitle("\t\t\t\t" + details.get(info.position).getName()
				+ " - " + details.get(info.position).getSub());
		menu.add(Menu.NONE, v.getId(), 0, "Edit");
		menu.add(Menu.NONE, v.getId(), 0, "Delete");
		menu.add(Menu.NONE, v.getId(), 0, "Delete All");
		menu.add(Menu.NONE, v.getId(), 0, "Cancel");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Edit") {

			Intent x = new Intent(ViewOtherDiseases.this, editPressure.class);
			x.putExtra("key1", rowId);
			Log.i("Sending", "" + rowId);
			ViewOtherDiseases.this.startActivity(x);

		} else if (item.getTitle() == "Delete") {

			showDialog(DIALOG_ID);
			
		} else if (item.getTitle() == "Delete All") {

			Toast.makeText(getApplicationContext(), "Confirm First",
					Toast.LENGTH_LONG).show();
			Intent con = new Intent(ViewOtherDiseases.this, confirm2.class);
			ViewOtherDiseases.this.startActivity(con);

		} else if (item.getTitle() == "Cancel") {

			Intent con = new Intent(ViewOtherDiseases.this, ViewOtherDiseases.class);
			ViewOtherDiseases.this.startActivity(con);
		} else {
			return false;
		}
		return true;
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		// selection.setText(stg1[position]);
		options.setEnabled(true);
		postn = list.get(position);
		rowId = Integer.parseInt(postn);
		Log.i("Data_postn", "" + rowId);
		String s = (String) ((TextView) v.findViewById(R.id.From)).getText();
		Toast.makeText(ViewOtherDiseases.this, s, Toast.LENGTH_LONG).show();

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
	public void onDestroy() {

		super.onDestroy();

		Intent back = new Intent(ViewOtherDiseases.this, MainMenu.class);
		ViewOtherDiseases.this.startActivity(back);

	}

	public void showAlertBox() {
		AlertDialog alertBox = null;
		alertBox = new AlertDialog.Builder(this).create();
		alertBox.setTitle("Update  Options");
		alertBox.setCancelable(true);

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
				Intent con = new Intent(ViewOtherDiseases.this, ViewOtherDiseases.class);
				ViewOtherDiseases.this.startActivity(con);

			}
		});

		del.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				isInternetPresent = connection.isConnectingToInternet();
				
				if(isInternetPresent){
				
					showDialog(DIALOG_ID);	
					
			}else{
				
				showAlertDialog(ViewOtherDiseases.this, "Internet Connection",
						"No Internet Connection", false);
				
			}
			}
		});

		deleteall.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {

				Toast.makeText(getApplicationContext(), "Confirm First",
						Toast.LENGTH_LONG).show();
				Intent con = new Intent(ViewOtherDiseases.this, confirm2.class);
				ViewOtherDiseases.this.startActivity(con);
			}
		});

		edit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent x = new Intent(ViewOtherDiseases.this, editPressure.class);
				x.putExtra("key1", rowId);
				Log.i("Sending", "" + rowId);
				ViewOtherDiseases.this.startActivity(x);

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
									String tablename = "otherdiseases";
									dm.deleteItem(rowId);
								
									Intent i = new Intent(ViewOtherDiseases.this, deleteFromServer.class);
									alertBox.dismiss();
									i.putExtra("pid",rowId);
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
	
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
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
