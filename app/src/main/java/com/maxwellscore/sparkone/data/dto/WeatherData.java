package com.maxwellscore.sparkone.data.dto;

import com.google.gson.annotations.SerializedName;
import com.maxwellscore.sparkone.data.DataConstants;

public class WeatherData {
    @SerializedName(DataConstants.KEY_CITY_NAME)
    private String cityName;
    @SerializedName(DataConstants.KEY_TEMPERATURE_DATA)
    private TemperatureData temperatureData;
    @SerializedName(DataConstants.KEY_WEATHER_TYPE)
    private String weatherType;

    public WeatherData() {
    }

    public WeatherData(String cityName, TemperatureData temperatureData, String weatherType) {
        this.cityName = cityName;
        this.temperatureData = temperatureData;
        this.weatherType = weatherType;
    }

    public String getCityName() {
        return cityName;
    }

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }

    public String getWeatherType() {
        return weatherType;
    }
}
