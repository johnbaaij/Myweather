package nl.johnbaaij.myweather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    String[] locations;
    ListView list;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Context context = getApplicationContext();


        locations = getResources().getStringArray(R.array.locations);
        list = (ListView) this.findViewById(R.id.settingsList);

        adapter = new CustomAdapter(this , locations, null);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String location = String.valueOf(parent.getItemAtPosition(position));

                PreferenceManager.getDefaultSharedPreferences(context).edit().putString("city", location).commit();

                Toast.makeText(context, "Locatie opgeslagen", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();

            }
        });


    }
}
