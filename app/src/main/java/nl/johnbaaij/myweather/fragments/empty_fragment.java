package nl.johnbaaij.myweather.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.johnbaaij.myweather.R;

/**
 * Created by John on 16/06/2017.
 */

public class empty_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.empty_layout, container, false);
        return view;
    }
}
