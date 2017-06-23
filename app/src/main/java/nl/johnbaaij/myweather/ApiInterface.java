package nl.johnbaaij.myweather;

import nl.johnbaaij.myweather.models.Parser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by John on 22/06/2017.
 */

public interface ApiInterface {

    @GET("weather?q=amsterdam&APPID=0de2125cb9a3e3019c8972d6440d1056")
    Call<Parser> getWeather(@Query("city") String city);
}
