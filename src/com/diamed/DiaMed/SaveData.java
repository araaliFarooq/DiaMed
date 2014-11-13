package com.diamed.DiaMed;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SaveData extends Activity implements OnClickListener {
	private DatabaseManipulator dh;
	static final int DIALOG_ID = 0;
	static EditText editText1;
	static EditText editText3;
	static EditText editText4;
    static EditText editText5;
    static EditText editText6;
	static Button add;
	static Button home;
	static String myEditText1;
	static String myEditText3;
	static String myEditText4;
    static String myEditText5;
    static String myEditText6;
	static String dateTime,dateTime2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save);
		add = (Button) findViewById(R.id.button01add);
		add.setOnClickListener(this);
		home = (Button) findViewById(R.id.button01home);
		home.setOnClickListener(this);

		editText1 = (EditText) findViewById(R.id.name);

		editText3 = (EditText) findViewById(R.id.skypeId);
		editText4 = (EditText) findViewById(R.id.address);
        editText5 = (EditText) findViewById(R.id.duration);
        editText6 = (EditText) findViewById(R.id.startdate);

		// text watcher.....
		TextWatcher watcher = new LocalTextWatcher2();
		((TextView) editText1).addTextChangedListener(watcher);
		((TextView) editText3).addTextChangedListener(watcher);
		((TextView) editText4).addTextChangedListener(watcher);
        ((TextView) editText5).addTextChangedListener(watcher);
        ((TextView) editText6).addTextChangedListener(watcher);

		updateButtonState();

	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.button01home:
			Intent i = new Intent(SaveData.this, MainMenu.class);
			SaveData.this.startActivity(i);
			break;

		case R.id.button01add:

			Calendar ci = Calendar.getInstance();

			dateTime = "" + ci.get(Calendar.YEAR) + "-"
					+ (ci.get(Calendar.MONTH) + 1) + "-"
					+ ci.get(Calendar.DAY_OF_MONTH) + " "
					+ ci.get(Calendar.HOUR) + ":" + ci.get(Calendar.MINUTE)
					+ ":" + ci.get(Calendar.SECOND);
			
			/*dateTime2 = "" + ci.get(Calendar.YEAR) + "-"
					+ (ci.get(Calendar.MONTH) + 1) + "-"
					+ ci.get(Calendar.DAY_OF_MONTH) + " "
					+ ci.get(Calendar.HOUR) + ":" + ci.get(Calendar.MINUTE)
					+ ":" + ci.get(Calendar.SECOND);*/

			myEditText1 = ((TextView) editText1).getText().toString();
			myEditText3 = ((TextView) editText3).getText().toString();
			myEditText4 = ((TextView) editText4).getText().toString();
            myEditText5 = ((TextView) editText5).getText().toString();
            myEditText6 = ((TextView) editText6).getText().toString();

			this.dh = new DatabaseManipulator(this);
			this.dh.insert(dateTime ,myEditText1, myEditText3, myEditText4, myEditText5, myEditText6);

			showDialog(DIALOG_ID);

			break;

		}

	}

	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
					"Saved successfully ! Want to add more?")
					.setCancelable(false)
					.setPositiveButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent i = new Intent(SaveData.this,
											MainMenu.class);
									startActivity(i);

								}
							})
					.setNegativeButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent i = new Intent(SaveData.this,
											SaveData.class);
									startActivity(i);
								}
							});
			AlertDialog alert = builder.create();
			dialog = alert;
			break;

		default:

		}
		return dialog;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Intent i = new Intent(this, MainMenu.class);
		SaveData.this.startActivity(i);
	}

	void updateButtonState() {

	}

}

class LocalTextWatcher2 implements TextWatcher {

	private void updateButtonState() {
		// TODO Auto-generated method stub
		boolean enabled = checkEditText(SaveData.editText1)
				&& checkEditText(SaveData.editText3)
				&& checkEditText(SaveData.editText4)
				&& checkEditText(SaveData.editText5)
				&& checkEditText(SaveData.editText6);
		SaveData.add.setEnabled(enabled);
	}

	private boolean checkEditText(EditText edit) {
		return ((edit.getText().toString()).length() > 0);
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		updateButtonState();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

}
