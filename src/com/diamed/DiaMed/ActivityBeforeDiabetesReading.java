package com.diamed.DiaMed;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.diamed.DiaMed.library.JSONParser2;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ActivityBeforeDiabetesReading extends Activity {
	private static final int ID_ADD = 1;
	private static final int ID_ACCEPT = 2;
	private static final int ID_UPLOAD = 3;
	static int idz=0;
	private static DatabaseManipulator dh;
	static String activity;
 	static final int DIALOG_ID = 0;
 	JSONParser2 jsonParser = new JSONParser2();
	

	// url to create new product
	static String url = "http://10.0.2.2/diamed/android_login_api/create_product.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radiogrp);
		RadioGroup radiogroup = (RadioGroup)findViewById(R.id.radioGrp);
	       final Button submit = (Button)findViewById(R.id.btnsub); 
	       final Button cancel = (Button)findViewById(R.id.btnclose);
	       
	       
    
   Intent x = getIntent();
   final float value2 = x.getFloatExtra("key1",0);
   final String value3 = x.getStringExtra("key2");
   

   Calendar ci = Calendar.getInstance();

 final String date = "" + ci.get(Calendar.DAY_OF_MONTH) + "/" + 
        (ci.get(Calendar.MONTH) + 1) + "/" +
        ci.get(Calendar.YEAR) ;
 final String Time = "" +
        ci.get(Calendar.HOUR)+ ":" +
        ci.get(Calendar.MINUTE)+ ":" +
        ci.get(Calendar.SECOND);
   
   Log.i("TAG_value2", date + Time);
   
			ActionItem addItem 		= new ActionItem(ID_ADD, "Enter + Save", getResources().getDrawable(R.drawable.ic_add));
			ActionItem acceptItem 	= new ActionItem(ID_ACCEPT, "Enter", getResources().getDrawable(R.drawable.ic_accept));
	        ActionItem uploadItem 	= new ActionItem(ID_UPLOAD, "Save", getResources().getDrawable(R.drawable.ic_up));
	       
	        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
	        uploadItem.setSticky(true);
	        
			final QuickAction mQuickAction 	= new QuickAction(ActivityBeforeDiabetesReading.this);
			
			mQuickAction.addActionItem(addItem);
			mQuickAction.addActionItem(acceptItem);
			mQuickAction.addActionItem(uploadItem);
			
			//setup the action item click listener
			mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@SuppressWarnings({ "unused", "static-access", "deprecation" })
				@Override
				public void onItemClick(QuickAction quickAction, int pos, int actionId) {
					ActionItem actionItem = quickAction.getActionItem(pos);
					
					if (actionId == ID_ADD) {
						Toast.makeText(getApplicationContext(), "Data Will Also Be Saved", Toast.LENGTH_SHORT).show();
						
						ActivityBeforeDiabetesReading.this.dh = new DatabaseManipulator(ActivityBeforeDiabetesReading.this);
						ActivityBeforeDiabetesReading.this.dh.insert3( date,Time,activity,value3);
						//new addNewValue(date, Time, activity, value3).execute();
						
						Intent y = new Intent(ActivityBeforeDiabetesReading.this,RecomendationForDiabetes.class);
						y.putExtra("key2", value2);
						y.putExtra("key3",idz);
						ActivityBeforeDiabetesReading.this.startActivity(y);
						
					} else if(actionId ==ID_ACCEPT){
						
						Toast.makeText(getApplicationContext(), "Data Will Not Be Saved", Toast.LENGTH_SHORT).show();
						
						Intent y = new Intent(ActivityBeforeDiabetesReading.this,RecomendationForDiabetes.class);
						y.putExtra("key2", value2);
						y.putExtra("key3",idz);
						ActivityBeforeDiabetesReading.this.startActivity(y);
						
					}else if(actionId ==ID_UPLOAD){
						
						Toast.makeText(getApplicationContext(), "Data Will Only Be Saved", Toast.LENGTH_SHORT).show();
						ActivityBeforeDiabetesReading.this.dh = new DatabaseManipulator(ActivityBeforeDiabetesReading.this);
						ActivityBeforeDiabetesReading.this.dh.insert3( date,Time,activity,value3);
						//new addNewValue(date, Time, activity, value3).execute();
						
						showDialog(DIALOG_ID);
					}
				}
			});
			
			mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
				@Override
				public void onDismiss() {
					Toast.makeText(getApplicationContext(), "Options dismissed", Toast.LENGTH_SHORT).show();
				}
			});
	
	       
	    radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				switch (checkedId) {
				case R.id.option1:
					submit.setEnabled(true);
					activity = "Before A Meal";
					idz = 1;
					break;
				case R.id.option2:
					submit.setEnabled(true);
					activity = "After A Meal";
					idz = 2;
					break;
				case R.id.option3:
					submit.setEnabled(true);
					activity = "Bed Time";
					idz = 3;
					break;
				}
				Intent y = new Intent(ActivityBeforeDiabetesReading.this,RecomendationForDiabetes.class);
				y.putExtra("key3",idz);
				
			}
		});
	    
	    cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent bck = new Intent(ActivityBeforeDiabetesReading.this, DiabetesValue.class);
				ActivityBeforeDiabetesReading.this.startActivity(bck);
				
			}
		});
	    
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println(idz);
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
		switch(id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Information saved successfully!  Want To Carry Out Another Task?")
			.setCancelable(false)
			.setPositiveButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					
					Intent d = new Intent(ActivityBeforeDiabetesReading.this,MainMenu.class);
					ActivityBeforeDiabetesReading.this.startActivity(d);

              }
			})
			.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
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
	    public boolean onCreateOptionsMenu(Menu menu){
	        super.onCreateOptionsMenu(menu);
	        int groupId = 0;
	        int menuItemOrder = Menu.NONE;
	        // Create menu ids for the event handler to reference
	        int menuItemId1 = Menu.FIRST;
	        int menuItemId2 = Menu.FIRST+1;
	        int menuItemId3 = Menu.FIRST+2;
	        int menuItemId4 = Menu.FIRST+3;
	        // Create menu text
	        int menuItemText1 = R.string.quit;
	        int menuItemText2 = R.string.help_title;
	        int menuItemText3 = R.string.settings_title;
	        int menuItemText4 = R.string.about_title;
	        // Add the items to the menu
	        MenuItem menuItem1 = menu.add(groupId, menuItemId1, menuItemOrder, menuItemText1)
	                .setIcon(R.drawable.ic_menu_close_clear_cancel);
	        MenuItem menuItem2 = menu.add(groupId, menuItemId2, menuItemOrder, menuItemText2)
	                .setIcon(R.drawable.ic_menu_help);
	        MenuItem menuItem3 = menu.add(groupId, menuItemId3, menuItemOrder, menuItemText3)
	                .setIcon(R.drawable.ic_menu_preferences);
	        MenuItem menuItem4 = menu.add(groupId, menuItemId4, menuItemOrder, menuItemText4)
	        		.setIcon(R.drawable.ic_menu_info_details);
	        return true;
	    }
	 	
	 	/** Handle events from the popup options menu above.  */

	 	public boolean onOptionsItemSelected(MenuItem item){
	        super.onOptionsItemSelected(item);
	        switch(item.getItemId()){
	        	// Actions for exit button
	            case (Menu.FIRST):
	                finish();
	                return true;
	            case (Menu.FIRST+1):
	                // Actions for help page
	                Intent i = new Intent(this, Help.class);
	                startActivity(i);
	                return true;
	            case(Menu.FIRST+2):
	                // Actions for preferences page
	                Intent j = new Intent(this, Prefs.class);
	                startActivity(j);
	                return true;	
	            case(Menu.FIRST+3):
	            	// Actions for about page
	            	Intent k = new Intent(this, About.class);
	            	startActivity(k);
	            	return true;
	        }
	        return false;
	    }
	
	 	
	 
}
