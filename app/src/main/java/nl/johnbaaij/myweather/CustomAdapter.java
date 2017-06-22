package nl.johnbaaij.myweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Kim Wijfje on 22-6-2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    String[] subtext;
    TextView textView;
    TextView subText;


    public CustomAdapter(Context context, String[] resource, String[] subText) {
        super(context, R.layout.text_layout , resource);
        this.subtext = subText;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater  = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.text_layout, parent, false);


        String singleQuestion = getItem(position);


        textView =(TextView) view.findViewById(R.id.TextCity);
        subText =(TextView) view.findViewById(R.id.subtext);



        textView.setText(singleQuestion);
        subText.setText(subtext[position]);

        return view;
    }
}

