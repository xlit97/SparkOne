package com.maxwellscore.kotlinexample.data

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import kotlin.random.Random

class MockWeatherApi {
    fun getWeather(): JSONObject {
        val json = JSONObject()
        try {
            json.put(DataConstants.KEY_CITY_NAME, "Якутск")
            val temperatureJson = JSONObject()
            temperatureJson.put(DataConstants.KEY_TEMPERATURE, Random.nextInt(5, 30))
            temperatureJson.put(DataConstants.KEY_UNIT, "C")
            json.put(DataConstants.KEY_TEMPERATURE_DATA, temperatureJson)
            val types = listOf(DataConstants.CLOUDY_TYPE, DataConstants.SUNNY_TYPE, DataConstants.RAIN_TYPE)
            json.put(DataConstants.KEY_WEATHER_TYPE, types.random())
        } catch (e: JSONException) {
            Log.e(this.javaClass.canonicalName, "Exception handled", e)
        }
        return json
    }
}