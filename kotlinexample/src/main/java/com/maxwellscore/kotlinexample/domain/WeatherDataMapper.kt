package com.maxwellscore.kotlinexample.domain

import com.maxwellscore.kotlinexample.data.DataConstants
import com.maxwellscore.kotlinexample.data.dto.WeatherData
import com.maxwellscore.kotlinexample.domain.entities.Weather
import com.maxwellscore.kotlinexample.domain.entities.WeatherType

class WeatherDataMapper {
    fun dataToDomain(data: WeatherData): Weather {
        val temperatureData = data.temperatureData
        val temperature = "${temperatureData.temperature}°${temperatureData.unit}"
        return Weather(data.cityName, temperature, getWeatherTypeFromString(data.weatherType))
    }

    private fun getWeatherTypeFromString(type: String) = when (type) {
        DataConstants.CLOUDY_TYPE -> WeatherType.CLOUDY
        DataConstants.SUNNY_TYPE -> WeatherType.SUNNY
        DataConstants.RAIN_TYPE -> WeatherType.RAIN
        else -> throw IllegalArgumentException("Такого типа погоды не существует")
    }
}