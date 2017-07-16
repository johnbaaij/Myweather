package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import nl.johnbaaij.myweather.ApiInterface;
import nl.johnbaaij.myweather.MainActivity;
import nl.johnbaaij.myweather.R;
import nl.johnbaaij.myweather.models.Weather.Current;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kim Wijfje on 22-6-2017.
 */

public class Text extends Fragment {
    TextView scrollable;
    TextView title;

    TextView temp;
    TextView minTemp;
    TextView maxTemp;

    String locations;
    String subtext;

    String API_KEY = MainActivity.API_KEY;
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    final String DEGREE  = "\u00b0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.locations = bundle.getString("locations");
            this.subtext = bundle.getString("subtext");
            //Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT).show();


        }

        View view = inflater.inflate(R.layout.text_clicked_layout, container, false);


        temp = (TextView)view.findViewById(R.id.temp);
        minTemp = (TextView)view.findViewById(R.id.minTemp);
        maxTemp = (TextView)view.findViewById(R.id.maxTemp);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);

        Call<Current> call = client.getWeather(locations, API_KEY, "NL");

        call.enqueue(new Callback<Current>() {
            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {
                Log.d("Success", "Success") ;

                nl.johnbaaij.myweather.models.Weather.Main main = response.body().getMain();



                //de api geeft de waardes terug in kelvin. Daarom moet er -273 gedaan worden om het naar celcius te krijgen.
                //We ronden het daarna af met 2 cijfers achter de comma.


                double tempValue = Math.round((main.getTemp() -273)  * 100.0) / 100.0;
                double tempMinValue = Math.round((main.getTempMin() -273)  * 100.0) / 100.0;
                double tempMaxValue = Math.round((main.getTempMax() -273)  * 100.0) / 100.0;


                temp.setText(getResources().getString(R.string.temp) + " " +String.valueOf(tempValue) + DEGREE);
                minTemp.setText(getResources().getString(R.string.min_temp) + " " +String.valueOf(tempMinValue) + DEGREE);
                maxTemp.setText(getResources().getString(R.string.max_temp) + " " + String.valueOf(tempMaxValue) + DEGREE);




            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {
                Toast.makeText(getActivity(), "Geen verbinding met de server", Toast.LENGTH_LONG).show();

                //print t to console to get an error message
                Log.e("server failed", t.toString());
            }
        });



        title = (TextView) view.findViewById(R.id.TextCity);
        title.setText(locations);

        //Enabling scrolling on TextView.
        return view;
    }
}
