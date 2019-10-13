package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.telecom.Call;
import android.app.Activity;

import java.util.Date;

import java.util.*;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;

public class TempHumidityPage extends AppCompatActivity {
    private Parser p;
    public TempHumidityPage(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        p = new Parser("http://192.168.88.161:1337/disconnect/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_humidity_page);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        ArrayList<String> list = p.getTemperature();
        DataPoint[] arr = new DataPoint[list.size()];
        Date t1 = null;
        Date t2 = null;
        for(int i = 0;i < list.size(); i++){
            //Date time=new Date((long)Integer.parseInt(list.get(i).split(":")[0])/1000);
            //long time = System.currentTimeMillis(Integer.parseInt(list.get(i).split(":")[0]))/1000;
            long timestamp = Integer.parseInt(list.get(i).split(":")[0]);
            System.out.println(timestamp);
            java.util.Date time = new java.util.Date(timestamp*1000);
            if(i == 0) t1 = time;
            if(i == list.size()-1) t2 = time;
            System.out.println("Plotting "+ time +" with "+ Integer.parseInt(list.get(i).split(":")[1]));
            arr[i] = new DataPoint(time,Integer.parseInt(list.get(i).split(":")[1]));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(arr);
        graph.addSeries(series);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.DKGRAY);

        series.setTitle("Temperature");


        series.setColor(Color.YELLOW);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

        graph.getViewport().setMinX(t1.getTime());
        graph.getViewport().setMaxX(t2.getTime());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setHumanRounding(false);


        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getGridLabelRenderer().setTextSize(40);
        graph.getGridLabelRenderer().setNumHorizontalLabels(6);
        graph.getGridLabelRenderer().setGridColor(Color.LTGRAY);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.LTGRAY);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.LTGRAY);

        /*
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(3);
        nf.setMinimumIntegerDigits(2);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));

         */

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return new Date((long) value).toString().substring(12,16);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " C";
                }
            }
        });



        //graph 2 for humidity


        list = p.getHumidity();
        DataPoint[] arr2 = new DataPoint[list.size()];

        for(int i = 0;i < list.size(); i++){
            Date time=new Date((long)Integer.parseInt(list.get(i).split(":")[0])*1000);
            if(i == 0) t1 = time;
            if(i == list.size()-1) t2 = time;

            arr2[i] = new DataPoint(time,Integer.parseInt(list.get(i).split(":")[1]));
        }

        GraphView graph2 = (GraphView) findViewById(R.id.graph2);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(arr2);
        graph2.addSeries(series2);
        series2.setDrawBackground(true);
        series2.setBackgroundColor(Color.DKGRAY);

        series2.setTitle("Humidity");
        series2.setColor(Color.YELLOW);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(10);
        series2.setThickness(8);

        graph2.getViewport().setMinX(t1.getTime());
        graph2.getViewport().setMaxX(t2.getTime());
        graph2.getViewport().setXAxisBoundsManual(true);

        graph2.getViewport().setMinY(0);
        graph2.getViewport().setMaxY(10);
        graph2.getViewport().setXAxisBoundsManual(true);

        graph2.getGridLabelRenderer().setTextSize(40);
        graph2.getGridLabelRenderer().setNumHorizontalLabels(6);
        graph2.getGridLabelRenderer().setGridColor(Color.LTGRAY);
        graph2.getGridLabelRenderer().setVerticalLabelsColor(Color.LTGRAY);
        graph2.getGridLabelRenderer().setHorizontalLabelsColor(Color.LTGRAY);

        NumberFormat nf2 = NumberFormat.getInstance();
        nf2.setMinimumFractionDigits(3);
        nf2.setMinimumIntegerDigits(2);

        graph2.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf2, nf2));

        graph2.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value2, boolean isValueX2) {
                if (isValueX2) {
                    // show normal x values
                    return new Date((long) value2).toString().substring(12,16);
                } else {
                    // show currency for y values
                    return super.formatLabel(value2, isValueX2) + " %";
                }
            }
        });

        //graph 3 for pressure
        list = p.getPressure();

        GraphView graph3 = (GraphView) findViewById(R.id.graph3);
        for(String s : list){
            System.out.println("THIS: "+s);
        }
        DataPoint[] arr3 = new DataPoint[list.size()];
        t1 = null;
        t2 = null;
        for(int i = 0;i < list.size(); i++){
            Date time=new Date((long)Integer.parseInt(list.get(i).split(":")[0])*1000);
            if(i == 0) t1 = time;
            if(i == list.size()-1) t2 = time;

            arr3[i] = new DataPoint(time,Integer.parseInt(list.get(i).split(":")[1]));
        }
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>(arr3);
        graph3.addSeries(series3);
        series3.setDrawBackground(true);
        series3.setBackgroundColor(Color.DKGRAY);

        series3.setTitle("Pressure");
        series3.setColor(Color.YELLOW);
        series3.setDrawDataPoints(true);
        series3.setDataPointsRadius(10);
        series3.setThickness(8);

        graph3.getViewport().setMinX(t1.getTime());
        graph3.getViewport().setMaxX(t2.getTime());
        graph3.getViewport().setXAxisBoundsManual(true);

        graph3.getViewport().setMinY(0);
        graph3.getViewport().setMaxY(10);
        graph3.getViewport().setXAxisBoundsManual(true);

        graph3.getGridLabelRenderer().setTextSize(40);
        graph3.getGridLabelRenderer().setNumHorizontalLabels(6);
        graph3.getGridLabelRenderer().setGridColor(Color.LTGRAY);
        graph3.getGridLabelRenderer().setVerticalLabelsColor(Color.LTGRAY);
        graph3.getGridLabelRenderer().setHorizontalLabelsColor(Color.LTGRAY);

        NumberFormat nf3 = NumberFormat.getInstance();
        nf3.setMinimumFractionDigits(3);
        nf3.setMinimumIntegerDigits(2);

        graph3.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf3, nf3));

        graph3.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value3, boolean isValueX3) {
                if (isValueX3) {
                    // show normal x values
                    return new Date((long) value3).toString().substring(12,16);
                } else {
                    // show currency for y values
                    return super.formatLabel(value3, isValueX3) + " Pa";
                }
            }
        });


    }


}
