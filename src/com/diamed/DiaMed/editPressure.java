package com.diamed.DiaMed;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class editPressure extends ListActivity{
	
	 static AlertDialog alertBox = null;
	 private static int rowID;
		static Button cancel;
		static Button continu;
	    static EditText text;
	    static EditText text2;
	    static String systolic;
		static String diastolic;
	
		
		static String origValue = null;
		static List<String[]> names2 =null ;
		static String[] stg1;
		static String[] stg2;
		static String[] stg3;
		static String level ;
		int idz;
		
		DatabaseManipulator dm2 ;

	/* (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent x = getIntent();
        rowID = x.getIntExtra("key1", 0);
        Log.i("key1", ""+rowID);
        showAlertBox();
	}
	
	private void showAlertBox() {
      
		dm2 = new DatabaseManipulator(this);
		
        alertBox = new AlertDialog.Builder(this).create();
        alertBox.setTitle("\t\tEdit  Values");
        alertBox.setCancelable(false);
       

        final LayoutInflater inflater = LayoutInflater.from(this);
        final View alert_webview = inflater.inflate(R.layout.edit2, null);
        alertBox.setView(alert_webview);
        
        cancel = (Button)alert_webview.findViewById(R.id.button2);
        continu = (Button) alert_webview.findViewById(R.id.button1);
      
        
       Log.i("pressureID", ""+rowID);
       
      
        	
        	 try {
				names2 = dm2.selectPressureValues(rowID);

				stg1=new String[names2.size()]; 
				stg2=new String[names2.size()]; 
				stg3=new String[names2.size()]; 

				int x =0;
				String stg;
				String dia;
				String activity;

				for (String[] name : names2) {
					activity = name[3];
					stg = name[4];
					dia = name[5];
					
					
					stg1[x]=stg;
					stg2[x]=dia;
					stg3[x]= activity;
					x++;
					
					
				}

				

				
				
				text = (EditText) alert_webview.findViewById(R.id.syst);
				text2 = (EditText) alert_webview.findViewById(R.id.dias);
				
				text.setText(stg1[0]);
				text2.setText(stg2[0]);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				Log.i("pick for editing", e.toString());
			}
	
           
              cancel.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				Intent con = new Intent(editPressure.this,ViewPressure.class);
    				alertBox.dismiss();
    				editPressure.this.startActivity(con);
    				
    			}
    		});
    	    
    	    
          
            
            
            continu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                	
                	
                	systolic = text.getText().toString();
                	Log.i("edited", systolic);
                	diastolic = text2.getText().toString();
                	Log.i("edited", diastolic);
            
					if(dm2.updatePressure(rowID, systolic, diastolic)){
    				
                	Toast.makeText(getApplicationContext(), "Operation Successful", Toast.LENGTH_LONG).show();
                	Thread minawa = new Thread(){
			    		public void run(){
			    			try{
			    				
			    				Intent con = new Intent(editPressure.this,
										ViewPressure.class);
			    				alertBox.dismiss();
			    				editPressure.this.startActivity(con);
			    				sleep(2500);
			    			}catch(InterruptedException ee){
			    				ee.printStackTrace();
			    			}finally{
			    				
			    				
			    				String activity = stg3[0];
			    				
			    				
								if(activity.equals("Before Exercise")){
			    					idz = 1;
			    				}
			    				else if (activity.equals("After Exercise")){
			    					
			    					idz = 2;
			    					
			    				}else if(activity.equals("Bed Time")){
			    					
			    					idz = 3;
			    				}else if(activity.equals("After Bed")){
			    					
			    					idz = 4;
			    				}
			    			
			    				float systo = Float.parseFloat(stg1[0]);
			    				float dias = Float.parseFloat(stg2[0]);
			    				
			    				Intent y = new Intent(editPressure.this,RecomendationForPressure.class);
			    				y.putExtra("key5", systo);
								y.putExtra("key6", dias);
								y.putExtra("key7", idz);

								editPressure.this.startActivity(y);
			    			}
			    			
			    		}
			    	 };
			    	 minawa.start();
            }
    				else{
    					
    					Toast.makeText(getApplicationContext(), "Operation NOT Successful", Toast.LENGTH_LONG).show();
        				Intent con = new Intent(editPressure.this,ViewPressure.class);
        				editPressure.this.startActivity(con);
    				}
                }
        } );
            
            
            
            alertBox.show();

    	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
    	}      


