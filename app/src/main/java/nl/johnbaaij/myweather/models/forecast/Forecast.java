
package nl.johnbaaij.myweather.models.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<nl.johnbaaij.myweather.models.forecast.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Forecast() {
    }

    /**
     * 
     * @param message
     * @param cnt
     * @param cod
     * @param list
     * @param city
     */
    public Forecast(String cod, double message, int cnt, java.util.List<nl.johnbaaij.myweather.models.forecast.List> list, City city) {
        super();
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<nl.johnbaaij.myweather.models.forecast.List> getList() {
        return list;
    }

    public void setList(java.util.List<nl.johnbaaij.myweather.models.forecast.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
