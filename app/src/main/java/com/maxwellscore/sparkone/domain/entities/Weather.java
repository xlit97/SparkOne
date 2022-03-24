package com.maxwellscore.sparkone.domain.entities;

public class Weather {
    private String city;
    private String temperature;
    private WeatherType weatherType;

    public Weather(String city, String temperature, WeatherType weatherType) {
        this.city = city;
        this.temperature = temperature;
        this.weatherType = weatherType;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }
}
