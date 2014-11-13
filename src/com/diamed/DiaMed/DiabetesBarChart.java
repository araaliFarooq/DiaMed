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
import android.util.Log;

public class DiabetesBarChart extends AbstractChart {

	List<String> list = new ArrayList<String>();
	List<String[]> list1 = new ArrayList<String[]>();

	DatabaseManipulator dm;
	List<String[]> names2 = new ArrayList<String[]>();
	String[] diaValue;
	
	double[] systolic;

	/**
	 * Returns the chart name.
	 * 
	 * @return the chart name
	 */
	public String getName() {
		return "Blood Glucose Levels";
	}

	/**
	 * Returns the chart description.
	 * 
	 * @return the chart description
	 */
	public String getDesc() {
		return "The monthly Blodd Glucose Readings (vertical range chart)";
	}

	/**
	 * Executes the chart demo.
	 * 
	 * @param onActionItemClickListener
	 *            the context
	 * @return the built intent
	 */
	public Intent execute(Context context) {

		dm = new DatabaseManipulator(context);

		names2 = dm.selectAll3();

		diaValue = new String[names2.size()];

		int x = 0;
		double level = 0.0;
		String stg;
		
		for (String[] name : names2) {
			stg = name[4];
			

			
			diaValue[x] = stg;
			
			x++;
		}


		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		RangeCategorySeries series = new RangeCategorySeries("Blood Glucose Level");
		int length = diaValue.length;
		
		double[] minValue = new double[length];

		for (int i = 0; i < length; i++) {
			
			minValue[i] = level;
		}
		
		for (int k = 0; k < length; k++) {
Log.i("min value", diaValue[k]+"/"+minValue[k]);

			try {
				
				series.add(minValue[k],Double.parseDouble(diaValue[k]));
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				Log.i("adding to series", e.toString());
			}

		}
		dataset.addSeries(series.toXYSeries());

		int[] colors = new int[] { Color.BLUE };
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		setChartSettings(renderer, "Daily Blood Glucose Levels", "Month",
				"Glucose Level(mmol/L)", 0.5, 12.5, 0, 45, Color.GRAY, Color.LTGRAY);
		// renderer.setBackgroundColor(Color.DKGRAY);
		// renderer.setApplyBackgroundColor(true);
		// renderer.setBarSpacing(0.5);
		
		int x1;
		
		for(x1=1; x1<= length; x1++ ){
	    	
	    	
	    	x1+=1;
	    }
		
		renderer.setXLabels(x1);
		renderer.setYLabels(10);
		
		for(double z=1; z <= 366; z++){
			
		renderer.addTextLabel(z, ""+z);
		
		}
		/*renderer.addTextLabel(3, "Mar");
		renderer.addTextLabel(5, "May");
		renderer.addTextLabel(7, "Jul");
		renderer.addTextLabel(10, "Oct");
		renderer.addTextLabel(12, "Dec");*/
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesTextSize(12);
		return ChartFactory.getRangeBarChartIntent(context, dataset, renderer,
				Type.DEFAULT, "Blood Glucose range");
	}

	

	
	
	
}
