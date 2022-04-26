package com.maxwellscore.kotlinexample.data

import com.maxwellscore.kotlinexample.data.dto.WeatherData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WeatherService {

    @GET("/weather")
    fun getWeather(): Observable<WeatherData>

    @POST("/location")
    fun postLocatiton(@Body location: String): Completable
}