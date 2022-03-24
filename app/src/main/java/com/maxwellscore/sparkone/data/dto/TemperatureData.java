package com.maxwellscore.sparkone.data.dto;

import com.google.gson.annotations.SerializedName;
import com.maxwellscore.sparkone.data.DataConstants;

public class TemperatureData {

    @SerializedName(DataConstants.KEY_TEMPERATURE)
    private double temperature;
    @SerializedName(DataConstants.KEY_UNIT)
    private String unit;

    public TemperatureData() {
    }

    public TemperatureData(double temperature, String unit) {
        this.temperature = temperature;
        this.unit = unit;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getUnit() {
        return unit;
    }
}
