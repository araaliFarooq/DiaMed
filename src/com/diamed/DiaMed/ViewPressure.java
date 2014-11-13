package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Protectable;







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

public class ViewPressure extends ListActivity  {     
	TextView selection;
	public int idToModify; 
	DatabaseManipulator dm;
	static  Button options;
	String fontPath = "fonts/MyriadPro-BoldCond.otf";
	static Typeface tf ;
	String postn;
	Button del;
	Button deleteall;
	Button cancel;
	Button edit;
	static final int DIALOG_ID = 0;
	static int identifier;
    static int rowId;
    MessageInRow Detail;
    protected AlertDialog alertBox = null;

    Boolean isInternetPresent = false;
	ConnectionDetector connection;

ListView msgList;
ArrayList<MessageInRow> details;
AdapterView.AdapterContextMenuInfo info;



    List<String> list = new ArrayList<String>();
	List<String[]> names2 =null ;
	String[] stg1;
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		try {
			setContentView(R.layout.listview);
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("Listview", e.toString());
		}
		
		connection = new ConnectionDetector(getApplicationContext());
		
		msgList = (ListView) findViewById(R.id.list);
		
		connection = new ConnectionDetector(getApplicationContext());
		
		msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				msgList.setBackgroundColor(color.background_dark);
				options.setEnabled(true);
				postn = list.get(position);
				rowId = Integer.parseInt(postn);
				 Log.i("Data_postn",""+rowId);
				 String s =(String) ((TextView) v.findViewById(R.id.From)).getText();
		         Toast.makeText(ViewPressure.this, s, Toast.LENGTH_LONG).show();

				
			}
		});
		
		 details = new ArrayList<MessageInRow>();
		 
		//font style.
		tf = Typeface.createFromAsset(getAssets(), fontPath);
		
		  dm = new DatabaseManipulator(this);
		 
		  registerForContextMenu(msgList);
		  
		  //final int pressureId = dm.fetchId3();
			 
			 options =(Button)findViewById(R.id.opt);
				options.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						// TODO Auto-generated method stub
						
				Intent op = new Intent(ViewPressure.this, HbPressureOptions.class);
				op.putExtra("key", rowId);
				
				ViewPressure.this.startActivity(op);
				
						
					}
				});
				
	      names2 = dm.selectAll4();

		stg1=new String[names2.size()]; 

		int x=0;
		String stg;

		for (String[] name : names2) {
			
			 Detail = new MessageInRow();
			
			stg = name[1]+" - "+name[2]+ " - "+name[3]+ " - "+name[4];
			
			list.add(name[0]);
			//Detail.setIcon(R.drawable.more);
            Detail.setName(name[4]);
            Detail.setSub(name[5]);
            Detail.setDesc(name[3]);
            Detail.setTime(name[1] +" "+ name[2]);
            details.add(Detail);
            
			x++;
		}


		msgList.setAdapter(new customAdapter(details , this));
        
		
	}      

	
	
	 @Override
	 public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	            // TODO Auto-generated method stub
	            super.onCreateContextMenu(menu, v, menuInfo);      
	             
	            
	                info =  (AdapterContextMenuInfo) menuInfo;
	  
	           menu.setHeaderTitle("\t\t\t\t"+details.get(info.position).getName() + " - " + details.get(info.position).getSub() );      
	                menu.add(Menu.NONE, v.getId(), 0, "Edit");
	                menu.add(Menu.NONE, v.getId(), 0, "Delete");
	                menu.add(Menu.NONE, v.getId(), 0, "Delete All");
	                menu.add(Menu.NONE, v.getId(), 0, "Cancel"); 
	        }
	         
	 @Override
	 public boolean onContextItemSelected(MenuItem item) {
	         if (item.getTitle() == "Edit") {

	        	 Intent x = new Intent(ViewPressure.this,editPressure.class);
	        		x.putExtra("key1",rowId);
	        		Log.i("Sending", ""+rowId);
					ViewPressure.this.startActivity(x);
	        	 
	                }
	          else if (item.getTitle() == "Delete") {

	        	
	        		  
	        	  showDialog(DIALOG_ID);
	        	 
	        	  
	                }
	          else if (item.getTitle() == "Delete All") {

	        	  Toast.makeText(getApplicationContext(), "Confirm First", Toast.LENGTH_LONG).show();
					Intent con = new Intent(ViewPressure.this,confirm2.class);
					ViewPressure.this.startActivity(con);
					
	            }
	          else  if(item.getTitle() == "Cancel"){
	        	  
	        	  Intent con = new Intent(ViewPressure.this,ViewPressure.class);
					ViewPressure.this.startActivity(con);
					finish();
	          }
	          else   {
	                return false;
	                }
	        return true;
	        }

	
	

	
	public void onListItemClick(ListView parent, View v, int position, long id) {
		//selection.setText(stg1[position]);
		options.setEnabled(true);
		postn = list.get(position);
		rowId = Integer.parseInt(postn);
		 Log.i("Data_postn",""+rowId);
		 String s =(String) ((TextView) v.findViewById(R.id.From)).getText();
         Toast.makeText(ViewPressure.this, s, Toast.LENGTH_LONG).show();

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
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}



	@Override
	public void onDestroy() {
	
		
	super.onDestroy();
	
	Intent back = new Intent(ViewPressure.this, MainMenu.class);
	ViewPressure.this.startActivity(back);
	
	
	}
	
	
	

	
}
