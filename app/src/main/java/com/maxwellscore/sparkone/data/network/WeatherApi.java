package com.maxwellscore.sparkone.data.network;

import static com.maxwellscore.sparkone.data.DataConstants.*;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Это симуляция ответа с сервера. На самом деле это был бы не просто JSON, а Observable или Future
 * так как запрос на сервер должен быть асинхронной задачей(то есть выполняемой в другом потоке)
 */
public class WeatherApi {

    public JSONObject getWeather(){
        JSONObject json = new JSONObject();
        try {
            json.put(KEY_CITY_NAME, "Якутск");
            JSONObject temperatureJson = new JSONObject();
            temperatureJson.put(KEY_TEMPERATURE, ThreadLocalRandom.current().nextInt(0, 31));
            temperatureJson.put(KEY_UNIT, "C");
            json.put(KEY_TEMPERATURE_DATA, temperatureJson);
            List<String> types = new ArrayList<>();
            types.add(CLOUDY_TYPE);
            types.add(SUNNY_TYPE);
            types.add(RAIN_TYPE);
            json.put(KEY_WEATHER_TYPE, types.get(new Random().nextInt(types.size())));
        } catch (JSONException e) {
            Log.e(this.getClass().getCanonicalName(), "Exception handled", e);
        }
        return json;
    }
}
