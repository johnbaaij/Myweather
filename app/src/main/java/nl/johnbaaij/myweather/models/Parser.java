package nl.johnbaaij.myweather.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 22/06/2017.
 */

public class Parser {



    @SerializedName("main")
    private Main main;

    public Main getMain() {
        return main;
    }

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
