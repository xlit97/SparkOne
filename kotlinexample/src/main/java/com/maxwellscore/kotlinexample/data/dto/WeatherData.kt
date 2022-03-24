package com.maxwellscore.kotlinexample.data.dto

import com.google.gson.annotations.SerializedName
import com.maxwellscore.kotlinexample.data.DataConstants

data class WeatherData(
    @SerializedName(DataConstants.KEY_CITY_NAME)
    val cityName: String,
    @SerializedName(DataConstants.KEY_TEMPERATURE_DATA)
    val temperatureData: TemperatureData,
    @SerializedName(DataConstants.KEY_WEATHER_TYPE)
    val weatherType: String
)
