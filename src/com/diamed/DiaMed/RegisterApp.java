package com.diamed.DiaMed;

import org.json.JSONException;
import org.json.JSONObject;

import com.diamed.DiaMed.library.DatabaseHandler;
import com.diamed.DiaMed.library.UserFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterApp extends Activity {
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	private static boolean isInternetPresent = false;
	private static ConnectionDetector connection;
	private static JSONObject json;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		connection = new ConnectionDetector(getApplicationContext());
		// Importing all assets like buttons, text fields
		inputFullName = (EditText) findViewById(R.id.registerName);
		inputEmail = (EditText) findViewById(R.id.registerEmail);
		inputPassword = (EditText) findViewById(R.id.registerPassword);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
		registerErrorMsg = (TextView) findViewById(R.id.register_error);

		isInternetPresent = connection.isConnectingToInternet();

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				String name = inputFullName.getText().toString();
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();

				UserFunctions userFunction = new UserFunctions();

				if (isInternetPresent) {

					json = userFunction.registerUser(name, email, password);

					// check for login response
					try {
						if (json.getString(KEY_SUCCESS) != null) {
							registerErrorMsg.setText("");
							String res = json.getString(KEY_SUCCESS);
							if (Integer.parseInt(res) == 1) {
								// user successfully registred
								// Store user details in SQLite Database
								DatabaseHandler db = new DatabaseHandler(
										getApplicationContext());
								JSONObject json_user = json
										.getJSONObject("user");

								// Clear all previous data in database
								userFunction
										.logoutUser(getApplicationContext());
								db.addUser(json_user.getString(KEY_NAME),
										json_user.getString(KEY_EMAIL),
										json.getString(KEY_UID),
										json_user.getString(KEY_CREATED_AT));
								// Launch Dashboard Screen
								Intent dashboard = new Intent(
										getApplicationContext(),
										DashboardActivity.class);
								// Close all views before launching Dashboard
								dashboard
										.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(dashboard);
								// Close Registration Screen
								finish();
							} else {
								// Error in registration
								registerErrorMsg
										.setText("Error:Name or Email/Phone Number already Exits ");
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {

					showAlertDialog(RegisterApp.this, "Internet Connection",
							"No Internet Connection", false);

				}
			}
		});

		// Link to Login Screen
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						LoginServer.class);
				startActivity(i);
				// Close Registration View
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent login = new Intent(RegisterApp.this, LoginUser.class);
		RegisterApp.this.startActivity(login);
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
