package nl.johnbaaij.myweather.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import nl.johnbaaij.myweather.CustomAdapter;
import nl.johnbaaij.myweather.R;

/**
 * Created by Kim Wijfje on 22-6-2017.
 */

public class Locaties extends Fragment {

    String[] locations;
    String[] subText;
    Activity activity;
    ListAdapter adapter;
    ListView locList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_layout, container, false);

        locations = getResources().getStringArray(R.array.locations);
        subText = getResources().getStringArray(R.array.subtext);
        activity = getActivity();
        adapter = new CustomAdapter(view.getContext() , locations, subText);
        locList = (ListView) view.findViewById(R.id.list_loc);
        locList.setAdapter(adapter);

        locList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String location = String.valueOf(parent.getItemAtPosition(position));
                String subtext = subText[position];

                //Toast.makeText(activity, question, Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();
                bundle.putString("city", location);
                bundle.putString("subText", subtext);


                // Create new fragment and transaction
                Fragment newFragment = new Text();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

            }
        });

        return view;
    }

}
