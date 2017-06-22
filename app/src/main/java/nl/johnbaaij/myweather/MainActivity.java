package nl.johnbaaij.myweather;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import nl.johnbaaij.myweather.fragments.Locaties;
import nl.johnbaaij.myweather.fragments.Voorspelling;
import nl.johnbaaij.myweather.fragments.Weer;
import nl.johnbaaij.myweather.models.CourseModel;
import nl.johnbaaij.myweather.models.Weather;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_weer:
                    changeToWeerFragment();
                    //mTextMessage.setText(R.string.menu_home);
                    return true;
                case R.id.navigation_voorspelling:
                    changeToVoorspellingFragment();
                    //mTextMessage.setText(R.string.menu_afspraken);
                    return true;
                case R.id.navigation_locaties:
                    changeToLocationsFragment();
                    //mTextMessage.setText(R.string.menu_faq);
                    return true;
            }
            return false;

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        requestSubjects();
    }

    public void changeToWeerFragment(){
        // Create new fragment and transaction
        Fragment newFragment = new Weer();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void changeToVoorspellingFragment(){

        // Create new fragment and transaction
        Fragment newFragment = new Voorspelling();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    public void changeToLocationsFragment(){


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

    private void requestSubjects(){
        Type type = new TypeToken<List<CourseModel>>(){}.getType();

        final GsonRequest<List<CourseModel>> request = new GsonRequest<List<CourseModel>>("http://fuujokan.nl/subject_lijst.json",
                type, null, new Response.Listener<List<CourseModel>>() {
            @Override
            public void onResponse(List<CourseModel> response) {
                processRequestSucces(response);
                //Log.d("succes" , );

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                processRequestError(error);
            }
        });
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }

    private void processRequestError(VolleyError error){
        // WAT ZULLEN WE HIERMEE DOEN ?? - niets..
    }

    private void processRequestSucces(List<CourseModel> subjects ){

        // putting all received classes in my database.
        for (CourseModel cm : subjects) {
            //ContentValues cv = new ContentValues();
            //cv.put(DatabaseInfo.CourseColumn.NAME, cm.name);
            //cv.put(DatabaseInfo.CourseColumn.GRADE, cm.grade);
            //cv.put(DatabaseInfo.CourseColumn.ECTS, cm.ects);
            //cv.put(DatabaseInfo.CourseColumn.CODE , cm.code);
            //DBHandler.insert(DatabaseInfo.CourseTables.COURSE, null, cv);
        }

        //Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.COURSE, new String[]{"*"}, null, null, null, null, null);
        //rs.moveToFirst();   // kan leeg zijn en faalt dan
        //DatabaseUtils.dumpCursor(rs);

    }

}
