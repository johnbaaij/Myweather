package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.johnbaaij.myweather.R;

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


        scrollable = (TextView) view.findViewById(R.id.subtext);
        scrollable.setText(subtext);

        title = (TextView) view.findViewById(R.id.TextCity);
        title.setText(locations);

        //Enabling scrolling on TextView.
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}
