package com.maxwellscore.sparkone.domain;


import static com.maxwellscore.sparkone.data.DataConstants.CLOUDY_TYPE;
import static com.maxwellscore.sparkone.data.DataConstants.RAIN_TYPE;
import static com.maxwellscore.sparkone.data.DataConstants.SUNNY_TYPE;

import com.maxwellscore.sparkone.data.dto.TemperatureData;
import com.maxwellscore.sparkone.data.dto.WeatherData;
import com.maxwellscore.sparkone.domain.entities.Weather;
import com.maxwellscore.sparkone.domain.entities.WeatherType;


public class WeatherDataMapper {

    public Weather dataToDomain(WeatherData data) {
        TemperatureData temperatureData = data.getTemperatureData();
        String temperature = String.format(
                "%s°%s",
                temperatureData.getTemperature(),
                temperatureData.getUnit()
        );
        return new Weather(data.getCityName(), temperature, getWeatherTypeFromString(data.getWeatherType()));
    }

    private WeatherType getWeatherTypeFromString(String type) {
        WeatherType result;
        switch (type) {
            case CLOUDY_TYPE:
                result = WeatherType.CLOUDY;
                break;
            case SUNNY_TYPE:
                result = WeatherType.SUNNY;
                break;
            case RAIN_TYPE:
                result = WeatherType.RAIN;
                break;
            default:
                throw new IllegalArgumentException("Такого типа погоды не существует");
        }
        return result;
    }
}
