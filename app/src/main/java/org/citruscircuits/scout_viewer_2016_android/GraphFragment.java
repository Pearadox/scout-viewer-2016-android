package org.citruscircuits.scout_viewer_2016_android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 2/5/16.
 */
public class GraphFragment extends Fragment {
    BarChart barChart;
    int[] xVals = new int[] {0, 1, 2, 3, 4, 5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        barChart = (BarChart)view.findViewById(R.id.chart);
        ArrayList<BarEntry> dataPoints = new ArrayList<BarEntry>();

        for (int x : xVals) {
            dataPoints.add(new BarEntry(getYForX(x), x));
        }

        BarDataSet dataSet = new BarDataSet(dataPoints, "Team 1678");
//        barChart.setX
//        dataSet.setAxisDependency(XAxis.XAxisPosition.BOTTOM);
//        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBorders(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawHighlightArrow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.setDrawMarkerViews(false);

        // use the interface ILineDataSet
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        List<String> labels = new ArrayList<>();
        labels.add("1.Q"); labels.add("2.Q"); labels.add("3.Q"); labels.add("4.Q"); labels.add("5.Q"); labels.add("6.Q");

        BarData data = new BarData(labels, dataSets);
        barChart.setData(data);
        barChart.setDescription("");
        barChart.getLegend().setEnabled(false);
        barChart.invalidate(); // refresh
    }

    public Float getYForX(int x) {
        return Float.valueOf(5.9f + x);
    }
}
