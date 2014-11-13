package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;

/**
 * Average temperature demo chart.
 */
public class pressureLineChart extends AbstractChart2 {
	
	List<String> list = new ArrayList<String>();
	List<String[]> list1 = new ArrayList<String[]>();
	
	DatabaseManipulator dm;
	List<String[]> names2 = new ArrayList<String[]>();
	String[] minValue;
	String[] maxValue;
	double [] systolic;
	static double []readings;
	static double []readings2;
	 static int z;
	
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Blood Pressure Ranges";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The average Blood Pressure Values Measured";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
	  
	  
	  dm = new DatabaseManipulator(context);
		
		names2 = dm.SelectPressureChartValue();
		
		minValue=new String[names2.size()]; 
		maxValue=new String[names2.size()]; 

		int x=0;
		String stg;
		String stg2;

		for (String[] name : names2) {
			stg = name[0];
			stg2 = name[1];
			
			
			minValue[x]=stg2;
			
			maxValue[x]=stg;
			
			x++;
		}
		
	  
		/*for (int i = 0; i < maxValue.length; i++) {
			
			systolic[i] = Double.parseDouble(maxValue[i]);
		}*/
		
	String[] titles = new String[] { "Systolic", "Diastolic" };
	
    List<double[]> y = new ArrayList<double[]>();
    
    
    for (int i = 0; i < titles.length; i++) {
     
    
      y.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
      
    }
    
    int arrayLength = maxValue.length;
    Log.i("Array length", ""+arrayLength);
    
    List<double[]> values = new ArrayList<double[]>();
   
    
    readings = new double[arrayLength];
    readings2  = new double[arrayLength];
    
    for ( int i =0; i < arrayLength; i++){
    	
    	double val = Double.parseDouble(maxValue[i]);
    	double val2 = Double.parseDouble(minValue[i]);
    	readings[i] = val;
    	readings2[i]= val2;
    }
    	

    values.add(readings);
    values.add(readings2);
    
    
    for (int i = 0; i < readings2.length; i++) {
		Log.i("systolic", "" + values.get(0)[i]);
		Log.i("daistolic", "" + values.get(1)[i]);
	}
    
	for(z=1; z<= arrayLength;z++ ){
    	
    	
    	z+=1;
    }
    int[] colors = new int[] { Color.GREEN, Color.YELLOW };
    PointStyle[] styles = new PointStyle[] { PointStyle.DIAMOND,
         PointStyle.SQUARE };
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
    }
    setChartSettings(renderer, "Average Blood Pressure Readings", "Day","Blood Pressure", 0,2, 0, 200,
        Color.LTGRAY, Color.LTGRAY);
    renderer.setXLabels(z);
    renderer.setYLabels(12);
    renderer.setShowGrid(true);
    renderer.setXLabelsAlign(Align.RIGHT);
    renderer.setYLabelsAlign(Align.RIGHT);
    renderer.setYLabelsAlign(Align.LEFT);
    renderer.setAxesColor(Color.GREEN);
    renderer.setPanLimits(new double[] { -10, 366, -10, 40 });
    renderer.setZoomLimits(new double[] { -10, 366, -10, 40 });
    Intent intent = ChartFactory.getLineChartIntent(context, buildDataset(arrayLength,titles, y, values),
        renderer, "Average Pressure");
    return intent;
  }

}
