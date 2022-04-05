package com.maxwellscore.ktor_weather_server

import kotlinx.serialization.Serializable

@Serializable
data class TemperatureData(
    val temperature: Int,
    val unit: String
)