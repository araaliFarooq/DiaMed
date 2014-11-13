package com.diamed.DiaMed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class showDiaLineChart extends Activity{

	DiabetesBarChart myClass = new DiabetesBarChart();
	Intent intent = null ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		intent = myClass.execute(this);
		startActivity(intent);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	/*@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Intent i = new Intent(showDiaLineChart.this,DisplayItems.class);
		this.startActivity(i);
	}*/

	
}
