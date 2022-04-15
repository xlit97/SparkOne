package com.maxwellscore.sparkone.data.network;

import com.maxwellscore.sparkone.data.dto.WeatherData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WeatherService {
    @GET("/weather")
    Observable<WeatherData> getWeather();

    @POST("/location")
    Observable<String> postLocation(@Body String location);
}
