package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.google.gson.Gson;

import java.util.ArrayList;

import nl.johnbaaij.myweather.ApiInterface;
import nl.johnbaaij.myweather.R;
import nl.johnbaaij.myweather.models.Main;
import nl.johnbaaij.myweather.models.Parser;
import nl.johnbaaij.myweather.models.TestModel;
import nl.johnbaaij.myweather.models.forecast.Forecast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by John on 05/07/2017.
 */

public class Geschiedenis extends Fragment {

    BarChart chart;
    double value;
    TestModel model;
    ArrayList<BarEntry> barEntries = new ArrayList<>(); //y - data
    double test = 676;

    float[] array = new float[5];
    String city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<String> xValues = new ArrayList<>();


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.array = bundle.getFloatArray("array");
            this.city = bundle.getString("city");
        }

        for (int i = 0; i < array.length; i++){

            barEntries.add(new BarEntry(array[i], i));
            xValues.add(Integer.toString(i * 3) + "uur");


        }


        BarDataSet barDataSet = new BarDataSet(barEntries, "Temperatuur");


        View view = inflater.inflate(R.layout.geschiedenis_layout, container, false);


        chart = (BarChart) view.findViewById(R.id.chart);


        BarData data = new BarData(xValues, barDataSet);
        chart.setData(data);

        return view;
    }

    public void setTest(double test){
        this.test = test;
    }

}
