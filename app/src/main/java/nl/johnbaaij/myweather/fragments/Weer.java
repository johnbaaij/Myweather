package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import nl.johnbaaij.myweather.ApiInterface;
import nl.johnbaaij.myweather.MainActivity;
import nl.johnbaaij.myweather.R;
import nl.johnbaaij.myweather.SettingsActivity;
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

    String city;

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    TextView textTemp;
    TextView textStad;
    TextView textMinTemp;
    TextView textMaxTemp;
    FloatingActionButton fab;

    //unicode karakter voor een graden symbool
    final String DEGREE  = "\u00b0";

    Retrofit retrofit;

    //onze api key. Deze is verborgen om misbruik te voorkomen.
    private final static String API_KEY = "0de2125cb9a3e3019c8972d6440d1056";



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.city = bundle.getString("city");

        }

        View view = inflater.inflate(R.layout.weer_layout, container, false);

        textTemp = (TextView)view.findViewById(R.id.textTemp);
        textMinTemp =(TextView)view.findViewById(R.id.textMinTemp);
        textMaxTemp =(TextView)view.findViewById(R.id.textMaxTemp);
        fab =(FloatingActionButton)view.findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                Bundle b = new Bundle();
                b.putString("name", "test"); 	// Your id
                intent.putExtras(b); 	// Put your id to your next Intent
                startActivity(intent);	// start
                getActivity().finish();
            }
        });


        textStad = (TextView)view.findViewById(R.id.textStad);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);

        Call <Parser> call = client.getWeather(city, API_KEY);

        call.enqueue(new Callback<Parser>() {
            @Override
            public void onResponse(Call<Parser> call, Response<Parser> response) {
                Log.d("Success", "Success") ;

                Main main = response.body().getMain();

                // De api geeft het weer x10 aan. Als het bijvoorbeeld 15 graden is dan geeft de api 150 aan. Daarom verkleinen we het eerst
                // en dan wordt het afgerond tot 2 cijfers achter de comma.

                double temp = Math.round((main.getTemp() * 10 / 100)  * 100.0) / 100.0;
                double tempMin = Math.round((main.getTemp_min() * 10 / 100)  * 100.0) / 100.0;
                double tempMax = Math.round((main.getTemp_max() * 10 / 100)  * 100.0) / 100.0;


                textTemp.setText(getResources().getString(R.string.temp) + " " +String.valueOf(temp) + DEGREE);
                textMinTemp.setText(getResources().getString(R.string.min_temp) + " " +String.valueOf(tempMin) + DEGREE);
                textMaxTemp.setText(getResources().getString(R.string.max_temp) + " " + String.valueOf(tempMax) + DEGREE);
                textStad.setText(response.body().getName());

            }

            @Override
            public void onFailure(Call<Parser> call, Throwable t) {
                Toast.makeText(getActivity(), "Geen verbinding met de server", Toast.LENGTH_LONG).show();

                //print t to console to get an error message
                Log.e("server failed", t.toString());
            }
        });

        return view;
    }

}
