package com.maxwellscore.kotlinexample.domain

import android.content.Context
import com.maxwellscore.kotlinexample.data.WeatherRepository
import com.maxwellscore.kotlinexample.domain.entities.Weather
import io.reactivex.rxjava3.core.Observable

// TODO Контекст был добавлен в качестве примера по Dagger 2. В следующей итерации надо удалить его
class WeatherInteractor constructor(context: Context) {
    private val repository: WeatherRepository = WeatherRepository()
    private val mapper: WeatherDataMapper = WeatherDataMapper()

    fun getWeather(): Observable<Weather> = repository.getWeather().map { mapper.dataToDomain(it) }

    fun postLocation(latitude: Double, longitude: Double) = repository.postLocation(latitude, longitude)
}