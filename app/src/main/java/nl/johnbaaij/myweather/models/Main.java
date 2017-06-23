package nl.johnbaaij.myweather.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John on 22/06/2017.
 */

public class Main {

    @SerializedName("temp")
    double temp;

    @SerializedName("pressure")
    int pressure;

    @SerializedName("humidity")
    int humidity;


    @SerializedName("temp_min")
    double temp_min;

    @SerializedName("temp_max")
    double temp_max;


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
