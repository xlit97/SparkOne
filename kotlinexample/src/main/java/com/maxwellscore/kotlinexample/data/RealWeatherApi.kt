package com.maxwellscore.kotlinexample.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RealWeatherApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)

    fun getWeather() = service.getWeather()

    fun postLocation(latitude: Double, longitude: Double) =
        service.postLocatiton("$latitude, $longitude")
}