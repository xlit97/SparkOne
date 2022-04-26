package com.maxwellscore.kotlinexample.data

import com.google.gson.Gson
import com.maxwellscore.kotlinexample.data.dto.WeatherData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class WeatherRepository {
    private val apiMock: MockWeatherApi = MockWeatherApi()
    private val api = RealWeatherApi()
    private val gson = Gson()

    fun getWeather(): Observable<WeatherData> = api.getWeather()

    fun postLocation(latitude: Double, longitude: Double): Completable = api.postLocation(latitude, longitude)
}