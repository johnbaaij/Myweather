package nl.johnbaaij.myweather;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.gson.Gson;

import java.util.ArrayList;

import nl.johnbaaij.myweather.fragments.Geschiedenis;
import nl.johnbaaij.myweather.fragments.Locaties;
import nl.johnbaaij.myweather.fragments.Over;
import nl.johnbaaij.myweather.fragments.Weer;
import nl.johnbaaij.myweather.models.forecast.Forecast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String city = "amsterdam";

    public final static String API_KEY = "0de2125cb9a3e3019c8972d6440d1056";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_weer:
                    changeToWeerFragment();
                    return true;
                case R.id.navigation_Info:
                    changeToOverFragment();
                    return true;
                case R.id.navigation_locaties:
                    changeToLocatiesFragment();
                    return true;

                case R.id.navigation_Geschiedenis:
                    changeToGeschiedenisFragment();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disableShiftMode();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String city = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city", null);


        if (city == null){
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city", "Amsterdam").commit();
        }

        changeToWeerFragment();
    }

    public void changeToWeerFragment(){
        // Create new fragment and transaction





        Bundle bundle = new Bundle();
        bundle.putString("city", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city", null));

        Fragment newFragment = new Weer();
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void changeToLocatiesFragment(){
        // Create new fragment and transaction

        Fragment newFragment = new Locaties();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void changeToOverFragment(){
        // Create new fragment and transaction

        Fragment newFragment = new Over();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void changeToGeschiedenisFragment(){
        // Create new fragment and transaction

       // bundle.putString("city", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city", null));

        city = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city", null);



        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiInterface client = retrofit.create(ApiInterface.class);

        Call<Forecast> call = client.getForecast(city, API_KEY);

        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {




                float[] array = new float[7];

                for(int i=0; i <6; i++){
                    array[i] = (float)(response.body().getList().get(i).getMain().getTemp() - 273 ) ;
                }
                Log.d("Success", "Success") ;



                Bundle bundle = new Bundle();

                bundle.putFloatArray("array", array);
                bundle.putString("city", city);

                Fragment newFragment = new Geschiedenis();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Geen verbinding met de server", Toast.LENGTH_LONG).show();

                //print t to console to get an error message
                Log.e("server failed", t.toString());
            }
        });






        Fragment newFragment = new Geschiedenis();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void disableShiftMode(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

}
