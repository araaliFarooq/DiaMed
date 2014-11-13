package com.diamed.DiaMed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class TaskReminder extends Activity {
	/** Called when the activity is first created. */
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 0;
	private Calendar c;
	private EditText nameEdit;
	private EditText descEdit;
	private Context mContext;
	private boolean dateFlag = false;
	private boolean timeFlag = false;
	private String time;
	private String contentTitle;
	private String contentText;
	public static int notificationCount;
	public Button dateButton;
	public Button timeButton;
	public Button reminButton;
	public TextListener textListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder);
		mContext = this;
		textListener = new TextListener();
		dateButton = (Button) findViewById(R.id.dateButton);
		timeButton = (Button) findViewById(R.id.timeButton);
		reminButton = (Button) findViewById(R.id.reminderButton);
		dateButton.setEnabled(false);
		timeButton.setEnabled(false);
		reminButton.setEnabled(false);
		nameEdit = (EditText) findViewById(R.id.nameEditText);
		nameEdit.addTextChangedListener(textListener);
		descEdit = (EditText) findViewById(R.id.DescEditText);
		descEdit.addTextChangedListener(textListener);
		c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
	}

	public class TextListener implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			if (descEdit.getText().length() == 0
					| nameEdit.getText().length() == 0) {
				dateButton.setEnabled(false);
				timeButton.setEnabled(false);
				reminButton.setEnabled(false);
			} else if (descEdit.getText().length() > 0
					& nameEdit.getText().length() > 0) {
				dateButton.setEnabled(true);
				timeButton.setEnabled(true);
				reminButton.setEnabled(true);
			}
		}
	}

	public void onReminderClicked(View view) {
		if (dateFlag & timeFlag == true) {
			notificationCount = notificationCount + 1;
			dateFlag = false;
			timeFlag = false;
			time = mYear + "-" + mMonth + "-" + mDay + " " + mHour + "-"
					+ mMinute;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm");
			Date dt = null;
			try {
				dt = df.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long when = dt.getTime();
			contentTitle = nameEdit.getText().toString();
			contentText = descEdit.getText().toString();
			AlarmManager mgr = (AlarmManager) mContext
					.getSystemService(Context.ALARM_SERVICE);
			Intent notificationIntent = new Intent(mContext, Reminder.class);
			notificationIntent.putExtra("Name", contentTitle);
			notificationIntent.putExtra("Description", contentText);
			notificationIntent.putExtra("NotifyCount", notificationCount);
			PendingIntent pi = PendingIntent.getBroadcast(mContext,
					notificationCount, notificationIntent,
					PendingIntent.FLAG_UPDATE_CURRENT);
			mgr.set(AlarmManager.RTC_WAKEUP, when, pi);
			Toast.makeText(mContext, "Your Reminder Activated",
					Toast.LENGTH_LONG).show();
			contentTitle = "";
			contentText = "";
			descEdit.setText("");
			nameEdit.setText("");
		} else if (dateFlag == false | timeFlag == false) {
			Toast.makeText(mContext, "Please choose Date &Time",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void onTimeClicked(View view) {
		showDialog(TIME_DIALOG_ID);
	}

	public void onDateClicked(View view) {
		showDialog(DATE_DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					false);
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return super.onCreateDialog(id);
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear + 1;
			mDay = dayOfMonth;
			dateFlag = true;
		}
	};
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			timeFlag = true;
		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent home = new Intent(TaskReminder.this, MainMenu.class);
		TaskReminder.this.startActivity(home);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
}
