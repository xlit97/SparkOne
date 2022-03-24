package com.maxwellscore.kotlinexample.domain

import com.maxwellscore.kotlinexample.data.WeatherRepository
import com.maxwellscore.kotlinexample.domain.entities.Weather

class WeatherInteractor {
    private val repository: WeatherRepository = WeatherRepository()
    private val mapper: WeatherDataMapper = WeatherDataMapper()

    fun getWeather(): Weather = mapper.dataToDomain(repository.getWeather())
}