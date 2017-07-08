package nl.johnbaaij.myweather;

import nl.johnbaaij.myweather.models.Weather.Current;
import nl.johnbaaij.myweather.models.forecast.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by John on 22/06/2017.
 */

public interface ApiInterface {

    @GET("weather")
    Call<Current> getWeather(
            @Query("q") String city,
            @Query("appid") String appid
            );


    @GET("forecast")
    Call<Forecast> getForecast(
            @Query("q") String city,
            @Query("appid") String appid
    );



}



