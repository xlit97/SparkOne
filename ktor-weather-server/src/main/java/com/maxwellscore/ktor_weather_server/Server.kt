package com.maxwellscore.ktor_weather_server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlin.random.Random

private val cities = listOf("Якутск", "Москва", "Алматы")
private val types = listOf("cloudy_weather", "sunny_weather", "rain_is_pouring")

fun main() {
    embeddedServer(Netty) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/weather") {
                val weatherData = WeatherData(
                    cityName = cities.random(),
                    temperatureData = TemperatureData(
                        temperature = Random.nextInt(10,30),
                        unit = "C"
                    ),
                    weatherType = types.random()
                )
                call.respond(weatherData)
            }
        }
    }.start(wait = true)
}