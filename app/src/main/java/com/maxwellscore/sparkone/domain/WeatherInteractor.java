package com.maxwellscore.sparkone.domain;

import com.maxwellscore.sparkone.data.WeatherRepository;
import com.maxwellscore.sparkone.domain.entities.Weather;

public class WeatherInteractor {

    private WeatherRepository repository = new WeatherRepository();
    private WeatherDataMapper mapper = new WeatherDataMapper();

    public Weather getWeather() {
        return mapper.dataToDomain(repository.getWeatherEasy());
    }
}
