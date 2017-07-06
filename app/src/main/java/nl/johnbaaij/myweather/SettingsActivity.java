package nl.johnbaaij.myweather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import nl.johnbaaij.myweather.models.Main;
import nl.johnbaaij.myweather.models.Parser;
import nl.johnbaaij.myweather.models.forecast.Forecast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsActivity extends AppCompatActivity {

    Button cancelButton, confirmButton;
    EditText cityName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Context context = getApplicationContext();

        cancelButton = (Button) this.findViewById(R.id.cancelButton);
        confirmButton = (Button) this.findViewById(R.id.ConfirmButton);
        cityName = (EditText) this.findViewById(R.id.cityName);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Instelling niet opgeslagen", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);	// start
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String message = cityName.getText().toString();
                if (message.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Voer een plaatsnaam in", Toast.LENGTH_LONG).show();
                }

                else{
                    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(MainActivity.BASE_URL).addConverterFactory(GsonConverterFactory.create());
                    Retrofit retrofit = builder.build();

                    ApiInterface client = retrofit.create(ApiInterface.class);

                    Call<Forecast> call = client.getForecast(message, MainActivity.API_KEY);

                    call.enqueue(new Callback<Forecast>() {
                        @Override
                        public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                            Log.d("Success", "Success") ;

                            String code;
                            try{
                                 code = response.body().getCod();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city", message).commit();
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);	// start
                                finish();

                            }

                            catch (Exception e){
                                Toast.makeText(context, "Plaats niet gevonden", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Forecast> call, Throwable t) {
                            Toast.makeText(context, "Geen verbinding met de server, probeer het later opnieuw", Toast.LENGTH_LONG).show();

                            //print t to console to get an error message
                            Log.e("server failed", t.toString());
                        }
                    });
                }
            }
        });
    }
}

