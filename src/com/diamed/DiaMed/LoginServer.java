package com.diamed.DiaMed;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

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

import com.diamed.DiaMed.library.DatabaseHandler;
import com.diamed.DiaMed.library.UserFunctions;

public class LoginServer extends Activity {
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;

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
		setContentView(R.layout.login);

		connection = new ConnectionDetector(getApplicationContext());

		// Importing all assets like buttons, text fields
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		isInternetPresent = connection.isConnectingToInternet();

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				Log.d("Button", "Login");

				if (isInternetPresent) {

					json = userFunction.loginUser(email, password);

					// check for login response
					try {
						if (json.getString(KEY_SUCCESS) != null) {
							loginErrorMsg.setText("");
							String res = json.getString(KEY_SUCCESS);
							if (Integer.parseInt(res) == 1) {
								// user successfully logged in
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

								// Close Login Screen
								finish();
							} else {
								// Error in login
								loginErrorMsg
										.setText("Incorrect username/password");
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				} else {

					showAlertDialog(LoginServer.this, "Internet Connection",
							"No Internet Connection", false);
				}
			}
		});

		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						RegisterApp.class);
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent login = new Intent(LoginServer.this, LoginUser.class);
		LoginServer.this.startActivity(login);
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
