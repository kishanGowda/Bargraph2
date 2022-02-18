package com.example.bargraph2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {


    BarChart mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mChart =(BarChart)findViewById(R.id.barchart);
        GroupBarChart();

    }

    public void GroupBarChart()  {
        mChart =  findViewById(R.id.barchart);
        mChart.setDrawBarShadow(false);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setTouchEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);

//
//            mChart.getAxisLeft().setDrawGridLines(false);
//            mChart.getAxisRight().setDrawGridLines(false);
//            mChart.getAxisRight().setEnabled(false);
//            mChart.animateY(1500);
//            mChart.getLegend().setEnabled(false);
//            mChart.getAxisRight().setDrawLabels(false);
//            mChart.setDoubleTapToZoomEnabled(false);
//            mChart.setOnChartValueSelectedListener(this);
            mChart.setDrawBarShadow(false);
            mChart.setDrawValueAboveBar(true);






        // empty labels so that the names are spread evenly
        String[] labels = {"", "11/07", "12/07", "13/07", "14/07", "15/07", "16/07","17/07",""};
        XAxis xAxis = mChart.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);

        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setTextSize(12);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularity(2);
        leftAxis.setLabelCount(8, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        mChart.getAxisRight().setEnabled(true);
        mChart.getLegend().setEnabled(true);

        float[] valOne = {40, 40, 40, 82, 60};
        float[] valTwo = {25, 25, 45, 20, 20};
        float[] valThree = {20, 20, 10, 20, 20};

        ArrayList<BarEntry> barOne = new ArrayList<>();
        ArrayList<BarEntry> barTwo = new ArrayList<>();
        ArrayList<BarEntry> barThree = new ArrayList<>();
        for (int i = 0; i < valOne.length; i++) {
            barOne.add(new BarEntry(i, valOne[i]));
            barTwo.add(new BarEntry(i, valTwo[i]));
            barThree.add(new BarEntry(i, valThree[i]));
        }

        BarDataSet set1 = new BarDataSet(barOne, "barOne");
        set1.setColor(Color.argb( 250, 152, 118,230));
        BarDataSet set2 = new BarDataSet(barTwo, "barTwo");
        set2.setColor(Color.rgb(16, 137, 255));
        BarDataSet set3 = new BarDataSet(barThree, "barTwo");
        set3.setColor(Color.RED);

        set1.setHighlightEnabled(true);
        set2.setHighlightEnabled(true);
        set3.setHighlightEnabled(true);
        set1.setDrawValues(true);
        set2.setDrawValues(true);
        set3.setDrawValues(true);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        BarData data = new BarData(dataSets);
        float groupSpace = 0.2f;
        float barSpace = 0f;
        float barWidth = 0.3f;
        // (barSpace + barWidth) * 2 + groupSpace = 1
        data.setBarWidth(barWidth);
        // so that the entire chart is shown when scrolled from right to left
        xAxis.setAxisMaximum(labels.length - 0.5f);
        mChart.setData(data);
        mChart.setScaleEnabled(false);
        mChart.setVisibleXRangeMaximum(7f);
        mChart.groupBars(1f, groupSpace, barSpace);
        mChart.invalidate();


                XYMarkerView mv = new XYMarkerView(this,new IndexAxisValueFormatter() );
                mv.setChartView(mChart); // For bounds control
           mChart.setMarker(mv);

            }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;


        mChart.getBarBounds((BarEntry) e);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);


        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }



    @Override
    public void onNothingSelected() {

    }
}