package nl.johnbaaij.myweather;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import nl.johnbaaij.myweather.fragments.Locaties;
import nl.johnbaaij.myweather.fragments.Over;
import nl.johnbaaij.myweather.fragments.Weer;

public class MainActivity extends AppCompatActivity {






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
            }
            return false;
        }




    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String city = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city", null);


        if (city == null){
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city", "Amsterdam").commit();
        }


        changeToWeerFragment();









        //mTextMessage = (TextView) findViewById(R.id.message);
        //requestSubjects();



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

}
