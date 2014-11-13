package com.diamed.DiaMed;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class diaSpeak extends Activity implements OnInitListener {

	private int MY_DATA_CHECK_CODE = 0;

	static float level;

	static int radioId;
	private TextToSpeech tts;

	static TextView outPut;
	static EditText inputText;
	static String text = null;
	static AlertDialog alertBox;
	static Button back, listen, finish;

	// static Button speakButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		Intent y = getIntent();
		text = y.getStringExtra("rec");
		level = y.getFloatExtra("level", 0);

		radioId = y.getIntExtra("radio", 0);

		showAlertBox();

		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				tts = new TextToSpeech(this, this);
			} else {
				Toast.makeText(getApplicationContext(),
						"Data Missing.Initiating Installation.",
						Toast.LENGTH_LONG).show();
				Intent installIntent = new Intent();
				installIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}

	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			Toast.makeText(diaSpeak.this,
					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG)
					.show();
		} else if (status == TextToSpeech.ERROR) {
			Toast.makeText(diaSpeak.this,
					"Error occurred while initializing Text-To-Speech engine",
					Toast.LENGTH_LONG).show();
		}
	}

	private void showAlertBox() {

		AlertDialog alertBox = new AlertDialog.Builder(this).create();
		alertBox.setTitle("Recommendation");
		alertBox.setCancelable(false);

		final LayoutInflater inflater = LayoutInflater.from(this);
		final View alert_webview = inflater.inflate(R.layout.speakxml, null);
		alertBox.setView(alert_webview);

		back = (Button) alert_webview.findViewById(R.id.tryButton);
		listen = (Button) alert_webview.findViewById(R.id.listen);
		finish = (Button) alert_webview.findViewById(R.id.exit);
		outPut = (TextView) alert_webview.findViewById(R.id.output);

		outPut.setText(text);

		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent y = new Intent(diaSpeak.this,
						RecomendationForDiabetes.class);
				y.putExtra("key2", level);
				y.putExtra("key3", radioId);

				diaSpeak.this.startActivity(y);

			}
		});

		listen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				if (text != null && text.length() > 0) {
					try {
						tts.speak(text, TextToSpeech.QUEUE_ADD, null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Log.i("tts", e.toString());
					}
				}

			}
		});

		finish.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tts != null) {
					tts.stop();
					tts.shutdown();
				}
				finish();

				Intent con = new Intent(diaSpeak.this, MainMenu.class);
				diaSpeak.this.startActivity(con);

			}
		});

		alertBox.show();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// finish();

	}

}