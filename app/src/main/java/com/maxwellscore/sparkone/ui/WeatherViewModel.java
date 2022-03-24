package com.maxwellscore.sparkone.ui;

import android.content.Context;
import android.util.Pair;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.maxwellscore.sparkone.R;
import com.maxwellscore.sparkone.domain.entities.Weather;
import com.maxwellscore.sparkone.domain.WeatherInteractor;
import com.maxwellscore.sparkone.domain.entities.WeatherType;

public class WeatherViewModel extends ViewModel {

    private static final String CITY_MASK = "{city}";

    private final MutableLiveData<WeatherUiState> liveData = new MutableLiveData<>();
    private final WeatherInteractor interactor = new WeatherInteractor();

    public LiveData<WeatherUiState> getWeatherLiveData() {
        return liveData;
    }

    /**
     * Метод вызывается когда activity завершит подготовительные работы. Затем метод идет в интерактор за данными,
     * которые потом преобразуются в данные ui слоя, которые удобно устанавливать для экрана
     */
    public void onCreate(Context context) {
        Weather weather = interactor.getWeather();
        String titleWithoutCity = context.getString(R.string.weather_in_mask);
        String title = titleWithoutCity.replace(CITY_MASK, weather.getCity());
        Pair<Integer, Integer> weatherAndColor = getWeatherTypeTextAndColor(
                weather.getWeatherType(),
                context
        );
        int weatherTextResId = weatherAndColor.first;
        int backgroundColor = weatherAndColor.second;
        liveData.postValue(new WeatherUiState(
                title,
                weather.getTemperature(),
                weatherTextResId,
                backgroundColor

        ));
    }

    private Pair<Integer, Integer> getWeatherTypeTextAndColor(WeatherType type, Context context) {
        int textId;
        int colorId;
        switch (type) {
            case CLOUDY:
                textId = R.string.cloudy_weather;
                colorId = R.color.light_blue;
                break;
            case SUNNY:
                textId = R.string.sunny_weather;
                colorId = R.color.orange;
                break;
            case RAIN:
                textId = R.string.rain;
                colorId = R.color.dark_light_blue;
                break;
            default:
                throw new IllegalArgumentException("Нет такой погоды");
        }
        return new Pair<>(textId, ContextCompat.getColor(context, colorId));
    }
}
