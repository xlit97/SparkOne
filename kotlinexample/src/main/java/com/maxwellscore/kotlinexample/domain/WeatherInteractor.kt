package com.maxwellscore.kotlinexample.domain

import com.maxwellscore.kotlinexample.data.WeatherRepository
import com.maxwellscore.kotlinexample.domain.entities.Weather
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class WeatherInteractor @Inject constructor() {

    private val repository: WeatherRepository = WeatherRepository()
    private val mapper: WeatherDataMapper = WeatherDataMapper()

    fun getWeather(): Observable<Weather> = repository.getWeather().map { mapper.dataToDomain(it) }

    fun postLocation(latitude: Double, longitude: Double) = repository.postLocation(latitude, longitude)
}