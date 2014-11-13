package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.RangeCategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class PressureBarChart extends AbstractChart2{

	List<String> list = new ArrayList<String>();
	List<String[]> list1 = new ArrayList<String[]>();
	
	DatabaseManipulator dm;
	List<String[]> names2 = new ArrayList<String[]>();
	String[] minValue;
	String[] maxValue;
	double [] systolic;

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
	    return "The monthly Pressure Readings (vertical range chart)";
	  }

	  /**
	   * Executes the chart demo.
	   * 
	   * @param onActionItemClickListener the context
	   * @return the built intent
	   */
	  public Intent execute(Context context) {
		    
	    dm = new DatabaseManipulator(context);
		
		names2 = dm.selectAll4();
		
		minValue=new String[names2.size()]; 
		maxValue=new String[names2.size()]; 

		int x=0;
		String stg;
		String stg2;

		for (String[] name : names2) {
			stg = name[4];//+" - "+name[2]+ " - "+name[3]+ " - "+name[4];
			stg2 = name[5];
			
			
			minValue[x]=stg;
			maxValue[x]=stg2;
			//double x1 = Double.parseDouble(stg1[x]);
			x++;
		}
		  
		  
	    //double[] minValues = new double[] { -24, -19, -10, -1, 7, 12, 15, 14, 9, 1, -11, -16 };
	    //double[] maxValues = new double[] { 7, 12, 24, 28, 33, 35, 37, 36, 28, 19, 11, 4 };

	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    RangeCategorySeries series = new RangeCategorySeries("Blood Pressure");
	    int length = minValue.length;
	    
	    for (int k = 0; k < length; k++) {
	    	
	      series.add(Double.parseDouble(minValue[k]),Double.parseDouble(maxValue[k]));
	      
	    }
	    dataset.addSeries(series.toXYSeries());

	    int[] colors = new int[] { Color.BLUE };
	    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
	    setChartSettings(renderer, "Daily Blood Pressure Ranges ", "Day", "Blood Pressure Readings(mm/Hg)", 0, 12,
	        35, 200, Color.GRAY, Color.LTGRAY);
	     //renderer.setBackgroundColor(Color.DKGRAY);
	     //renderer.setApplyBackgroundColor(true);
	    //renderer.setBarSpacing(0.5);
	    renderer.setXLabels(1);
	    renderer.setYLabels(10);
	    
	    for(double z=1; z <= 366; z++){
			
			renderer.addTextLabel(z, "");
			
			}
	    
	    /*renderer.addTextLabel(1, "Jan");
	    renderer.addTextLabel(3, "Mar");
	    renderer.addTextLabel(5, "May");
	    renderer.addTextLabel(7, "Jul");
	    renderer.addTextLabel(10, "Oct");
	    renderer.addTextLabel(12, "Dec");*/
	    
	    renderer.setDisplayChartValues(true);
	    renderer.setChartValuesTextSize(12);
	    return ChartFactory.getRangeBarChartIntent( context, dataset, renderer, Type.DEFAULT,
	        "Blood Pressure range");
	  }

	


	

}
