package com.maxwellscore.sparkone.data.network;

import com.google.gson.Gson;
import com.maxwellscore.sparkone.data.dto.WeatherData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealWeatherApi {

    private WeatherApi mockApi = new WeatherApi();
    private Gson gson = new Gson();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    WeatherService service = retrofit.create(WeatherService.class);

    public Observable<WeatherData> getWeatherFromNetwork() {
        //        return Observable.just(gson.fromJson(mockApi.getWeather().toString(), WeatherData.class));
        return service.getWeather();
    }
}
