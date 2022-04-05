package com.maxwellscore.ktor_weather_server

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val cityName: String,
    val temperatureData: TemperatureData,
    val weatherType: String
)