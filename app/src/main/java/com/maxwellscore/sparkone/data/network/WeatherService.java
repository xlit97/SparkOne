package com.maxwellscore.sparkone.data.network;

import com.maxwellscore.sparkone.data.dto.WeatherData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherService {
    @GET("/weather")
    Observable<WeatherData> getWeather();
}
