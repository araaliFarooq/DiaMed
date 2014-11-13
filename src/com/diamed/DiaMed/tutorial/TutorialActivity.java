package com.diamed.DiaMed.tutorial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.diamed.DiaMed.Backup;
import com.diamed.DiaMed.BackupBloodPressure;
import com.diamed.DiaMed.ControlPanel;
import com.diamed.DiaMed.MainMenu;
import com.diamed.DiaMed.Prefs;
import com.diamed.DiaMed.R;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TutorialActivity extends FragmentActivity {
	private ViewPager _mViewPager;
	private ViewPagerAdapter _adapter;
	private Button _btn1, _btn2, _btn3, _btn4;
	private static ImageButton cancel;
	private Button _btn5, _btn6, _btn7, _btn8, _btn9;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		boolean userdemo = Prefs
				.startUserDemoOnStartUp(getApplicationContext());
		setContentView(R.layout.tutorialmain);
		cancel = (ImageButton) findViewById(R.id.imageButton1);

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(TutorialActivity.this,
						MainMenu.class);
				TutorialActivity.this.startActivity(intent);

			}
		});

		if (userdemo) {
			showAlertDialog(
					TutorialActivity.this,
					"\t\t\t\t\t\t\tUSER DEMO",
					"\t\tYou Can Deactivate User Demo Auto startup from settings",
					false);
		}

		setUpView();
		setTab();
	}

	private void setUpView() {
		_mViewPager = (ViewPager) findViewById(R.id.viewPager);
		_adapter = new ViewPagerAdapter(getApplicationContext(),
				getSupportFragmentManager());
		_mViewPager.setAdapter(_adapter);
		_mViewPager.setCurrentItem(0);
		initButton();
	}

	private void setTab() {
		_mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int position) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				btnAction(position);
			}

		});

	}

	private void btnAction(int action) {
		switch (action) {
		case 0:
			setButton(_btn1, "1", 40, 40);
			setButton(_btn2, "", 20, 20);
			break;

		case 1:
			setButton(_btn2, "2", 40, 40);
			setButton(_btn3, "", 20, 20);
			break;

		case 2:
			setButton(_btn3, "3", 40, 40);
			setButton(_btn4, "", 20, 20);
			break;

		case 3:
			setButton(_btn4, "4", 40, 40);
			setButton(_btn5, "", 20, 20);
			break;

		case 4:
			setButton(_btn5, "5", 40, 40);
			setButton(_btn6, "", 20, 20);
			break;

		case 5:
			setButton(_btn6, "6", 40, 40);
			setButton(_btn7, "", 20, 20);
			break;

		case 6:
			setButton(_btn7, "7", 40, 40);
			setButton(_btn8, "", 20, 20);
			break;

		case 7:
			setButton(_btn8, "8", 40, 40);
			setButton(_btn9, "", 20, 20);
			break;

		case 8:
			setButton(_btn9, "7", 40, 40);
			setButton(_btn1, "", 20, 20);
			break;

		}
	}

	private void initButton() {
		_btn1 = (Button) findViewById(R.id.btn1);
		_btn2 = (Button) findViewById(R.id.btn2);
		_btn3 = (Button) findViewById(R.id.btn3);
		_btn4 = (Button) findViewById(R.id.btn4);
		_btn5 = (Button) findViewById(R.id.btn5);
		_btn6 = (Button) findViewById(R.id.btn6);
		_btn7 = (Button) findViewById(R.id.btn7);
		_btn8 = (Button) findViewById(R.id.btn8);
		_btn9 = (Button) findViewById(R.id.btn9);

		setButton(_btn1, "1", 40, 40);
		setButton(_btn2, "", 20, 20);
		setButton(_btn3, "", 20, 20);
		setButton(_btn4, "", 20, 20);
		setButton(_btn5, "", 20, 20);
		setButton(_btn6, "", 20, 20);
		setButton(_btn7, "", 20, 20);
		setButton(_btn8, "", 20, 20);
		setButton(_btn9, "", 20, 20);
	}

	private void setButton(Button btn, String text, int h, int w) {
		btn.setWidth(w);
		btn.setHeight(h);
		btn.setText(text);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		Intent intent = new Intent(TutorialActivity.this, MainMenu.class);
		TutorialActivity.this.startActivity(intent);
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
		alertDialog.setIcon(R.drawable.success);

		// Setting OK Button

		alertDialog.setButton("GO TO SETTINGS",
				new DialogInterface.OnClickListener() {
			
			int id = 1;
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(TutorialActivity.this,
								Prefs.class);
						i.putExtra("intentId", id);
						TutorialActivity.this.startActivity(i);
						alertDialog.dismiss();
					}
				});

		alertDialog.setButton2("CANCEL", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				alertDialog.dismiss();
			}

		});

		// Showing Alert Message
		alertDialog.show();
	}

}