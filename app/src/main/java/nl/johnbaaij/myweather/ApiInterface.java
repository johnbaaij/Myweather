package nl.johnbaaij.myweather;

import nl.johnbaaij.myweather.models.Parser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by John on 22/06/2017.
 */

public interface ApiInterface {

    @GET("weather")
    Call<Parser> getWeather(
            @Query("q") String city,
            @Query("appid") String appid
            );

}




//api.openweathermap.org/data/2.5/forecast/daily?id=524901



