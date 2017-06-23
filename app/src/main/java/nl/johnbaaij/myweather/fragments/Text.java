package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

    String locations;
    String subtext;


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








        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);

        Call<Parser> call = client.getWeather(city, API_KEY);

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



            }

            @Override
            public void onFailure(Call<Parser> call, Throwable t) {
                Toast.makeText(getActivity(), "Geen verbinding met de server", Toast.LENGTH_LONG).show();

                //print t to console to get an error message
                Log.e("server failed", t.toString());
            }
        });


        scrollable = (TextView) view.findViewById(R.id.subtext);
        scrollable.setText(subtext);

        title = (TextView) view.findViewById(R.id.TextCity);
        title.setText(locations);

        //Enabling scrolling on TextView.
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}
