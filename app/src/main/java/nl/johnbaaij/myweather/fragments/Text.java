package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import nl.johnbaaij.myweather.ApiInterface;
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

public class Text extends Fragment {
    TextView scrollable;
    TextView title;

    TextView temp;
    TextView minTemp;
    TextView maxTemp;

    String locations;
    String subtext;

    private final static String API_KEY = "0de2125cb9a3e3019c8972d6440d1056";
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

        Call<Parser> call = client.getWeather(locations, API_KEY);

        call.enqueue(new Callback<Parser>() {
            @Override
            public void onResponse(Call<Parser> call, Response<Parser> response) {
                Log.d("Success", "Success") ;

                Main main = response.body().getMain();


                // De api geeft het weer x10 aan. Als het bijvoorbeeld 15 graden is dan geeft de api 150 aan. Daarom verkleinen we het eerst
                // en dan wordt het afgerond tot 2 cijfers achter de comma.

                double tempValue = Math.round((main.getTemp() * 10 / 100)  * 100.0) / 100.0;
                double tempMinValue = Math.round((main.getTemp_min() * 10 / 100)  * 100.0) / 100.0;
                double tempMaxValue = Math.round((main.getTemp_max() * 10 / 100)  * 100.0) / 100.0;


                temp.setText(getResources().getString(R.string.temp) + " " +String.valueOf(tempValue) + DEGREE);
                minTemp.setText(getResources().getString(R.string.min_temp) + " " +String.valueOf(tempMinValue) + DEGREE);
                maxTemp.setText(getResources().getString(R.string.max_temp) + " " + String.valueOf(tempMaxValue) + DEGREE);


            }

            @Override
            public void onFailure(Call<Parser> call, Throwable t) {
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
