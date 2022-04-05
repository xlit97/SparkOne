package com.maxwellscore.sparkone.domain;

import com.maxwellscore.sparkone.data.WeatherRepository;
import com.maxwellscore.sparkone.domain.entities.Weather;

import io.reactivex.rxjava3.core.Observable;

/**
 * Является представителем слоя UseCase(Domain) и он и модели(дата классы) описывают бизнес логику приложения или фичи
 * В данном случае бизнес-логика простая: Отдать пользователю информацию о погоде
 */
public class WeatherInteractor {

    private WeatherRepository repository = new WeatherRepository();
    private WeatherDataMapper mapper = new WeatherDataMapper();

    public Observable<Weather> getWeather() {
        return repository.getWeatherEasy()
                .map(weatherData -> mapper.dataToDomain(weatherData));
    }
}
