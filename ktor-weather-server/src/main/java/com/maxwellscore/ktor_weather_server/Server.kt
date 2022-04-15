package com.maxwellscore.ktor_weather_server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
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
            post("/location") {
                val posted = call.receiveText()
                call.respondText("Местоположение получено", status = HttpStatusCode.Accepted)
                LoggerFactory.getLogger("MyLogger").info("Тек. местоположение: $posted")
            }
        }
    }.start(wait = true)
}