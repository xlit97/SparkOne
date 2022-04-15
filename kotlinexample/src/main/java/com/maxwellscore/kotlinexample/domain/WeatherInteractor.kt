package com.maxwellscore.kotlinexample.domain

import com.maxwellscore.kotlinexample.data.WeatherRepository
import com.maxwellscore.kotlinexample.domain.entities.Weather
import io.reactivex.rxjava3.core.Observable

class WeatherInteractor {
    private val repository: WeatherRepository = WeatherRepository()
    private val mapper: WeatherDataMapper = WeatherDataMapper()

    fun getWeather(): Observable<Weather> = repository.getWeather().map { mapper.dataToDomain(it) }
}