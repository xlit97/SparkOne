package com.maxwellscore.kotlinexample.ui

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxwellscore.kotlinexample.R
import com.maxwellscore.kotlinexample.domain.WeatherInteractor
import com.maxwellscore.kotlinexample.domain.entities.Weather
import com.maxwellscore.kotlinexample.domain.entities.WeatherType

class MainViewModel : ViewModel() {

    companion object {
        private const val CITY_MASK = "{city}"
    }

    private val interactor: WeatherInteractor = WeatherInteractor()
    private val _liveData: MutableLiveData<WeatherUiState> = MutableLiveData<WeatherUiState>()
    val liveData: LiveData<WeatherUiState> = _liveData

    /**
     * Метод вызывается когда activity завершит подготовительные работы. Затем метод идет в интерактор за данными,
     * которые потом преобразуются в данные ui слоя, которые удобно устанавливать для экрана
     */
    fun onInitiallyCreated(context: Context) {
        updateWeather(context)
    }

    fun onScreenClicked(context: Context) {
        updateWeather(context)
    }

    private fun updateWeather(context: Context) {
        val weather: Weather = interactor.getWeather()
        val title =
            context.getString(R.string.weather_in_mask).replace(CITY_MASK, weather.city)
        val (weatherTextResId, backgroundColorId) = getWeatherTypeTextAndColorId(weather.weatherType)
        _liveData.postValue(
            WeatherUiState(
                title,
                weather.temperature,
                weatherTextResId,
                ContextCompat.getColor(context, backgroundColorId)
            )
        )
    }

    private fun getWeatherTypeTextAndColorId(type: WeatherType) =
        when (type) {
            WeatherType.CLOUDY -> {
                R.string.cloudy_weather to R.color.light_blue
            }
            WeatherType.SUNNY -> {
                R.string.sunny_weather to R.color.orange
            }
            WeatherType.RAIN -> {
                R.string.rain to R.color.dark_light_blue
            }
        }
}