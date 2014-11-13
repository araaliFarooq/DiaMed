package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.Arrays;
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
public class DiabetesLineChart extends AbstractChart {

	List<String> list = new ArrayList<String>();
	List<String[]> list1 = new ArrayList<String[]>();

	DatabaseManipulator dm;
	List<String[]> tableValues = new ArrayList<String[]>();
	String[] diaValue;
	static double glucoseLevel;
	double[] systolic;
	static int z;
	static double[] readings;

	/**
	 * Returns the chart name.
	 * 
	 * @return the chart name
	 */
	public String getName() {
		return "Blood Glucose Ranges";
	}

	/**
	 * Returns the chart description.
	 * 
	 * @return the chart description
	 */
	public String getDesc() {
		return "The average Blood Glucose Levels Measured";
	}

	/**
	 * Executes the chart demo.
	 * 
	 * @param context
	 *            the context
	 * @return the built intent
	 */
	public Intent execute(Context context) {

		dm = new DatabaseManipulator(context);

		tableValues = dm.selectAll3();

		diaValue = new String[tableValues.size()];

		int x = 0;
		String stg;

		for (String[] name : tableValues) {

			stg = name[4];

			diaValue[x] = stg;

			x++;
		}

		int arrayLength = diaValue.length;

		String[] titles = new String[] { "Blood Glucose Level" };

		List<double[]> axisValues = new ArrayList<double[]>();
		// x-axis
		for (int i = 0; i < arrayLength; i++) {
			axisValues
					.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		}
		List<double[]> values = new ArrayList<double[]>();

		readings = new double[arrayLength];

		Log.i("array length", "" + arrayLength);

		for (int i = 0; i < arrayLength; i++) {

			Double val;
			try {
				val = Double.parseDouble(diaValue[i]);
				readings[i] = val;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				Log.i("parseDouble", e.toString());
			}
			Log.i("diavalue", "" + diaValue[i]);

		}

		values.add(readings);

		for (z = 1; z <= arrayLength; z++) {

			z += 1;
		}
		int[] colors = new int[] { Color.GREEN };
		PointStyle[] styles = new PointStyle[] { PointStyle.DIAMOND };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i1 = 0; i1 < length; i1++) {

			((XYSeriesRenderer) renderer.getSeriesRendererAt(i1))
					.setFillPoints(true);
		}
		setChartSettings(renderer, "Average Blood Glucose Level", "Day",
				"Glucose Level(mmol/L)", 1, 12, 0.5, 40, Color.LTGRAY,
				Color.LTGRAY);
		renderer.setXLabels(z);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.LEFT);
		renderer.setAxesColor(Color.GREEN);
		renderer.setPanLimits(new double[] { -10, 366, -10, 40 });
		renderer.setZoomLimits(new double[] { -10, 366, -10, 40 });
		Intent intent = ChartFactory.getLineChartIntent(context,
				buildDataset(arrayLength, titles, axisValues, values),
				renderer, "Average Glucose Level");
		return intent;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent home = new Intent(DiabetesLineChart.this, MainMenu.class);
		DiabetesLineChart.this.startActivity(home);
	}

}
