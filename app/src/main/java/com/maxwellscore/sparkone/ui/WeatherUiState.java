package com.maxwellscore.sparkone.ui;

public class WeatherUiState {

    private String title;
    private String temperature;
    private int weatherTextResId;
    private int backgroundColor;

    public WeatherUiState(String title, String temperature, int weatherTextResId, int backgroundColor) {
        this.title = title;
        this.temperature = temperature;
        this.weatherTextResId = weatherTextResId;
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public String getTemperature() {
        return temperature;
    }

    public int getWeatherTextResId() {
        return weatherTextResId;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
