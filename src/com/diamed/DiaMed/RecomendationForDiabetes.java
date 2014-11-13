package com.diamed.DiaMed;




import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RecomendationForDiabetes extends Activity {

	static String recommendation;
    static Button tryButton;
    static Button finish;
    static Button listen;
    static final int DIALOG_ID = 0;
    static  float level = 0;
    static  int radioId2 =0;
    View alert_webview;
    private static String pusername;
    static List<String[]> names2 = null;
    static String[] stg5;
	static String[] stg6;
	String s1;
	String s2;
    private static DatabaseManipulator dbase;
	private static String dcontact;
    private static int intent;
    private static int critical;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Intent y = getIntent();
		level = y.getFloatExtra("key2", 0);
		radioId2 = y.getIntExtra("key3", 0);
		
		dbase = new DatabaseManipulator(this);
		try {
			names2 = dbase.selectDocDetails();

			stg5 = new String[names2.size()];
			stg6 = new String[names2.size()];

			int x = 0;

			for (String[] name : names2) {
				s1 = name[1];
				s2 = name[5];

				stg5[x] = s1;
				stg6[x] = s2;
				x++;

			}

			pusername = stg5[0];

			dcontact = stg6[0];

		} catch (Exception e) {
			// TODO Auto-generated catch block

			Log.i("pick for editing", e.toString());
		}

		showAlertBox();
		
	}
	
private void showAlertBox() {
		
        AlertDialog alertBox = null;
        alertBox = new AlertDialog.Builder(this).create();
        Context mContext = getApplicationContext();
        alertBox.setTitle("\t\tRecommendation");
     
        alertBox.setCancelable(false);
   

        final LayoutInflater inflater = (LayoutInflater) (mContext).getSystemService(LAYOUT_INFLATER_SERVICE);
        
if ((level >= 3.9 && level <= 5.6) && radioId2 == 1) {



            final View alert_webview = inflater.inflate(R.layout.normalbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.normal);


        } else if ((level >= 5.6 && level <= 7.0) && radioId2 == 1) {

         

            final View alert_webview = inflater.inflate(R.layout.pdb,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.pdb);


        } else if ((level > 7.0 && level <= 25) && radioId2 == 1) {



            alert_webview = inflater.inflate(R.layout.vhighbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.vhighbg);
            critical= 1;


        } else if ((level > 8.3 && level <= 25) && radioId2 == 1) {



            alert_webview = inflater.inflate(R.layout.vhighbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.vhighbg);
            critical= 1;


        } else if ((level <= 3.9 && level > 2.8) && radioId2 == 1) {



            alert_webview = inflater.inflate(R.layout.bglow,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.bglow);


        } else if (level <= 2.8 && radioId2 == 1) {




             alert_webview = inflater.inflate(R.layout.vlowbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.vlowbg);
            critical= 1;


        } else if ((level >= 3.9 && level <= 7.8) && radioId2 == 2) {


           alert_webview = inflater.inflate(R.layout.normalbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.normal);


        } else if ((level >= 7.8 && level <= 11.1) && radioId2 == 2) {



           alert_webview = inflater.inflate(R.layout.pdb,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.pdb);


        } else if ((level <= 13.9 && level >= 11.1) && radioId2 == 2) {



             alert_webview = inflater.inflate(R.layout.bghigh,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.bghigh);


        } else if ((level > 13.9 && level <= 25) && radioId2 == 2) {



             alert_webview = inflater.inflate(R.layout.vhighbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.vhighbg);
            critical= 1;


        } else if ((level <= 3.9 && level > 2.8) && radioId2 == 2) {



             alert_webview = inflater.inflate(R.layout.bglow,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.bglow);


        } else if ((level <= 2.8) && radioId2 == 2) {


             alert_webview = inflater.inflate(R.layout.vlowbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.vlowbg);
            critical= 1;



        } else if ((level <= 7.8 && level >= 5.6) && radioId2 == 3) {


             alert_webview = inflater.inflate(R.layout.normalbg,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.normal);


        } else if ((level > 7.8) && radioId2 == 3) {


             alert_webview = inflater.inflate(R.layout.bghigh,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.bghigh);


        } else if ((level < 5.6) && radioId2 == 3) {

            alert_webview = inflater.inflate(R.layout.bglow,null);
            alertBox.setView(alert_webview);
            finish = (Button) alert_webview.findViewById(R.id.back1);
            listen = (Button) alert_webview.findViewById(R.id.listen);
            recommendation = getString(R.string.bglow);


        } else {

            Toast.makeText(getApplicationContext(), "Invalid Entry", Toast.LENGTH_LONG).show();
            alertBox.setMessage(""+level);
           alert_webview = inflater.inflate(R.layout.overrange,null);
            alertBox.setView(alert_webview);
            tryButton = (Button) alert_webview.findViewById(R.id.tryButton);
            listen =(Button) alert_webview.findViewById(R.id.listen);
            finish =(Button) alert_webview.findViewById(R.id.exit);

            recommendation = getString(R.string.overrange);

            try{
                //Activity alert_webview = null;

                tryButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        Intent f = new Intent(RecomendationForDiabetes.this, DiabetesValue.class);
                        RecomendationForDiabetes.this.startActivity(f);

                    }
                });
            }catch(Exception msg){

                Log.i("tryButton", ""+msg);
            }

        }
        
        //final View alert_webview = inflater.inflate(R.layout.presvalue, null);

           //alertBox.setView(alert_webview);
        
            try{
                //Activity alert_webview = null;
				
                finish.setOnClickListener(new View.OnClickListener() {
        			
                	@Override
    				public void onClick(View v) {
    					// TODO Auto-generated method stub

    					intent = 1;
    					if (critical == 1) {
    						showAlertDialog(
    								RecomendationForDiabetes.this,
    								"\t\t\tMESSAGE ALERT",
    								"Critical Reading Taken, Allow System To Alert Doctor",
    								false);

    					} else {
    						Toast.makeText(
    								getApplicationContext(),
    								"If Symptoms Persist...  Seek Qualified Medical Advice",
    								Toast.LENGTH_LONG).show();
    						Intent finisher = new Intent(RecomendationForDiabetes.this,
    								MainMenu.class);
    						RecomendationForDiabetes.this.startActivity(finisher);
    					}
    				}
        		});
                }catch(Exception msg){
                	
                	Log.i("finish", ""+msg);
                }
                
                
                    
                  
                        
                        try{
                            //Activity alert_webview = null;
            				
                            listen.setOnClickListener(new View.OnClickListener() {
                    			
                    			@Override
                    			public void onClick(View v) {
                    				// TODO Auto-generated method stub
                    				
                    				intent = 2;
                					if (critical == 1) {
                						showAlertDialog(
                								RecomendationForDiabetes.this,
                								"\t\t\tMESSAGE ALERT",
                								"Critical Reading Taken, Allow System \t\t\t\t\tTo Alert Doctor",
                								false);

                					} else {
                    				
                    Intent i = new Intent(RecomendationForDiabetes.this,diaSpeak.class);
                    i.putExtra("rec",recommendation);
                    i.putExtra("level", level);

                    i.putExtra("radio", radioId2);

                    RecomendationForDiabetes.this.startActivity(i);
                					}
                    			}
                    		});
                            }catch(Exception msg){
                            	
                            	Log.i("listen", ""+msg);
                            }
                
                    
        alertBox.show();

	}
	
	
	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch(id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("This Range Has No Recomendation.Seek Medical Assistance Or Try Again")
			.setCancelable(false)
			.setPositiveButton("TRY", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					
					Intent f = new Intent(RecomendationForDiabetes.this, DiabetesValue.class);
					RecomendationForDiabetes.this.startActivity(f);

              }
			})
			.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					
					Intent finisher = new Intent(RecomendationForDiabetes.this, MainMenu.class);
					RecomendationForDiabetes.this.startActivity(finisher);
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
	
//sending alert to doctor	
	private void sendSMS(String phoneNumber, String message) {
        String SENT = "ALERT_SENT";
        String DELIVERED = "ALERT_DELIVERED";
 
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
 
        // ---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
            	
                switch (getResultCode()) {
                
                	case Activity.RESULT_OK:
                	                	
                    		Toast.makeText(getBaseContext(), "SMS sent",
					Toast.LENGTH_SHORT).show();
                    		break;
                    
                	case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                	
                    		Toast.makeText(getBaseContext(), "Generic failure",
					Toast.LENGTH_SHORT).show();
                    		break;
                    
                	case SmsManager.RESULT_ERROR_NO_SERVICE:
                	
                    		Toast.makeText(getBaseContext(), "No service",
					Toast.LENGTH_SHORT).show();
                    		break;
                    
                	case SmsManager.RESULT_ERROR_NULL_PDU:
                	
                    		Toast.makeText(getBaseContext(), "Null PDU",
					Toast.LENGTH_SHORT).show();
                    		break;
                    
                	case SmsManager.RESULT_ERROR_RADIO_OFF:
                	
                    		Toast.makeText(getBaseContext(), "Radio off",
					Toast.LENGTH_SHORT).show();
                    		break;
                }
            }
        }, new IntentFilter(SENT));
 
        // ---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
            	
                switch (getResultCode()) {
                
                	case Activity.RESULT_OK:
                	
                    		Toast.makeText(getBaseContext(), "SMS delivered",
					Toast.LENGTH_SHORT).show();
                    		break;
                    
                	case Activity.RESULT_CANCELED:
                	
                    		Toast.makeText(getBaseContext(), "SMS not delivered",
					Toast.LENGTH_SHORT).show();
                    		break;
                }
            }
        }, new IntentFilter(DELIVERED));
 
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
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
		            	
		            	int id = 0;
		                // Actions for preferences page
		                Intent j = new Intent(this, Prefs.class);
		                j.putExtra("intentId", id);
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
		 	
		 	@SuppressWarnings("deprecation")
			public void showAlertDialog(Context context, String title, String message,
					Boolean status) {
				final AlertDialog alertDialog = new AlertDialog.Builder(context)
						.create();

				// Setting Dialog Title
				alertDialog.setTitle(title);

				// Setting Dialog Message
				alertDialog.setMessage(message);

				// Setting alert dialog icon
				alertDialog.setIcon(R.drawable.fail);

				// Setting OK Button

				alertDialog.setButton("PROCEED", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int which) {

						final String msg = "Critical readings,Glucose Level " + level + " " + "from " + pusername;
						try {
							sendSMS(dcontact, msg);
						} catch (final Exception e) {
							// TODO Auto-generated catch block
							Log.i("send-sms", e.toString());
							Toast.makeText(getApplicationContext(), "Message Not Sent.No Doctor Found In System.Register Doctor First Then Try Again", Toast.LENGTH_LONG).show();
						}
						alertDialog.dismiss();
						if (intent == 1) {
							final Intent finisher = new Intent(RecomendationForDiabetes.this, MainMenu.class);
							RecomendationForDiabetes.this.startActivity(finisher);

						} else if (intent == 2) {
							 final Intent i = new Intent(RecomendationForDiabetes.this,diaSpeak.class);
			                    i.putExtra("rec",recommendation);
			                    i.putExtra("level", level);

			                    i.putExtra("radio", radioId2);

			                    RecomendationForDiabetes.this.startActivity(i);
						}
					}
				});

				alertDialog.setButton2("CANCEL", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						alertDialog.dismiss();
						if (intent == 1) {
							Intent finisher = new Intent(RecomendationForDiabetes.this, MainMenu.class);
							RecomendationForDiabetes.this.startActivity(finisher);

						} else if (intent == 2) {
							Intent i = new Intent(RecomendationForDiabetes.this,diaSpeak.class);
		                    i.putExtra("rec",recommendation);
		                    i.putExtra("level", level);

		                    i.putExtra("radio", radioId2);

		                    RecomendationForDiabetes.this.startActivity(i);
						}
					}

				});

				// Showing Alert Message
				alertDialog.show();
			}
}
