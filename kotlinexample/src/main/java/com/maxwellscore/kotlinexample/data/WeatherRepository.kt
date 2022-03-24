package com.maxwellscore.kotlinexample.data

import com.google.gson.Gson
import com.maxwellscore.kotlinexample.data.dto.WeatherData

class WeatherRepository {
    private val api: WeatherApi = WeatherApi()
    private val gson = Gson()

    fun getWeather(): WeatherData =
        gson.fromJson(api.getWeather().toString(), WeatherData::class.java)
}