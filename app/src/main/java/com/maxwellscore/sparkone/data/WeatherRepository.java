package com.maxwellscore.sparkone.data;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.maxwellscore.sparkone.data.dto.TemperatureData;
import com.maxwellscore.sparkone.data.dto.WeatherData;
import com.maxwellscore.sparkone.data.network.WeatherApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Является представителем Data слоя(слоя данных) и ответственнен за связь с удаленным сервером и обработку ответа с сервера
 * Также он может связываться с базой данных
 */
public class WeatherRepository {

    private WeatherApi api = new WeatherApi();
    Gson gson = new Gson();

    @Nullable
    public WeatherData getWeather() {
        JSONObject response = api.getWeather();
        WeatherData result = null;
        try {
            result = jsonToDataEntityHard(response);
        } catch (JSONException e) {
            // ignore
        }
        return result;
    }

    @Nullable
    private WeatherData jsonToDataEntityHard(JSONObject json) throws JSONException {
        JSONObject temperatureDataJson = json.getJSONObject(DataConstants.KEY_TEMPERATURE_DATA);
        TemperatureData temperatureData = new TemperatureData(
                temperatureDataJson.getDouble(DataConstants.KEY_TEMPERATURE),
                temperatureDataJson.getString(DataConstants.KEY_UNIT)
        );
        return new WeatherData(
                json.getString(DataConstants.KEY_CITY_NAME),
                temperatureData,
                json.getString(DataConstants.KEY_WEATHER_TYPE)
        );
    }

    public WeatherData getWeatherEasy() {
        return gson.fromJson(api.getWeather().toString(), WeatherData.class);
    }
}
