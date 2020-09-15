package com.example.help;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.BarringInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.Month;
import java.util.ArrayList;


public class AnalysisFragment extends Fragment {
    BarChart cases_bar;
    private static final String[] YEARS = { "2019", "2018", "2017", "2016", "2015"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analysis,container,false);

        cases_bar = view.findViewById(R.id.cases_bar);
         //Appearance of Bar chart
        cases_bar.getDescription().setEnabled(false);
        cases_bar.setDrawValueAboveBar(false);
        cases_bar.animateY(5000);
        cases_bar.animateX(5000);

        XAxis xAxis = cases_bar.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return YEARS[(int) value];
            }
        });

        YAxis axisLeft = cases_bar.getAxisLeft();
        axisLeft.setGranularity(100f);
        axisLeft.setAxisMinimum(0);

        YAxis axisRight = cases_bar.getAxisRight();
        axisLeft.setGranularity(100f);
        axisLeft.setAxisMinimum(0);


        ArrayList <BarEntry> cases = new ArrayList<>();
        cases.add(new BarEntry(0,2144));
        cases.add(new BarEntry(1,2230));
        cases.add(new BarEntry(2,1480));
        cases.add(new BarEntry(3,1131));
        cases.add(new BarEntry(4,1089));



        BarDataSet bardataset = new BarDataSet(cases, "Cases");

        ArrayList<IBarDataSet> labels = new ArrayList<>();
        labels.add(bardataset);

        BarData data = new BarData( labels);
        cases_bar.setData(data); // set the data and list of labels
        data.setValueTextSize(12f);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        return view;
    }
}