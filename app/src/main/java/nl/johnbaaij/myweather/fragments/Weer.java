package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nl.johnbaaij.myweather.ApiInterface;
import nl.johnbaaij.myweather.DBHandler;
import nl.johnbaaij.myweather.MainActivity;
import nl.johnbaaij.myweather.R;
import nl.johnbaaij.myweather.models.Main;
import nl.johnbaaij.myweather.models.Parser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kim Wijfje on 22-6-2017.
 */

public class Weer extends Fragment {

    String city = "amsterdam";

    DBHandler dbHandler;


    public Weer() {
        this.dbHandler = new DBHandler(getActivity(), null, null, 1);

    }

    //JSONObject json;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    TextView textView;

    Retrofit retrofit;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weer_layout, container, false);

        textView = (TextView)view.findViewById(R.id.textView);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);

        Call <Parser> call = client.getWeather(city);

        call.enqueue(new Callback<Parser>() {
            @Override
            public void onResponse(Call<Parser> call, Response<Parser> response) {
                Toast.makeText(getActivity(), "goed", Toast.LENGTH_LONG).show();
                Log.d("Retro", "dit werkte") ;

                Main main = response.body().getMain();
                textView.setText(String.valueOf(main.getTemp()));
            }

            @Override
            public void onFailure(Call<Parser> call, Throwable t) {
                Toast.makeText(getActivity(), "Geen verbinding met de server", Toast.LENGTH_LONG).show();
            }
        });



        //client.getWeather("amsterdam");







        return view;
    }








}
