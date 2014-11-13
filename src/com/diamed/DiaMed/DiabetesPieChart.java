package com.diamed.DiaMed;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.renderer.DefaultRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

/**
 * Blood Glucose Level  pie chart.
 */
public class DiabetesPieChart extends AbstractChart3 {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Blood Glucose chart for Blood Glucose Level Range Counts";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "Blood Glucose chart for Blood Glucose Level Range Counts";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { 12, 14, 11, 10 });
    //values.add(new double[] { 10, 9, 14, 20});
    List<String[]> titles = new ArrayList<String[]>();
    titles.add(new String[] { "gL < 4", "5.3 =< gL >= 4", "gL >= 8", "7.5 <= gL >= 5.4" });
    //titles.add(new String[] { "Dia <= 60", "80 <= Dia >= 60", "Dia >= 90", "90 <= Dia >= 80"});
    int[] colors = new int[] { Color.MAGENTA, Color.GREEN, Color.RED, Color.YELLOW };
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setApplyBackgroundColor(true);
    renderer.setBackgroundColor(Color.TRANSPARENT/*rgb(222, 222, 200)*/);
    renderer.setLabelsColor(Color.WHITE);
    Toast.makeText(context, "Change Screen Orintation For Better Viewing", Toast.LENGTH_LONG).show();
    return ChartFactory.getDoughnutChartIntent(context, buildMultipleCategoryDataset(
        "Blood Glucose", titles, values), renderer, "Blood Glucose Level Range Counts");
  }

}
