package com.diamed.DiaMed;






import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Intro extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

        // Font path
		String fontPath1 ="fonts/adinekir.ttf";
        String fontPath = "fonts/MyriadPro-BoldCond.otf";
        //String fonty ="fonts/CircleD_Font_by_CrazyForMusic.ttf";
        String fonty ="fonts/Hawaii_Killer.ttf";
        
        // text view label
        TextView txtMajor = (TextView) findViewById(R.id.major);
       TextView txtMajor1 = (TextView) findViewById(R.id.major1);
        TextView txtMinor = (TextView) findViewById(R.id.minor);
        
        
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
      
		Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fonty);
        
        // Applying font
        txtMajor.setTypeface(tf);
        txtMajor1.setTypeface(tf);
        txtMinor.setTypeface(tf2);
        
        Thread minawa = new Thread(){
    		public void run(){
    			try{
    				sleep(3000);
    			}catch(InterruptedException ee){
    				
    				ee.printStackTrace();
    				
    			}finally{
    				Intent login = new Intent(Intro.this,LoginUser.class);
    				Intro.this.startActivity(login);
    			}
    			
    		}
    	 };
    	 minawa.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	
}
