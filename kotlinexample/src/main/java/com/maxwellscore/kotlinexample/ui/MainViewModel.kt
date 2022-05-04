package com.maxwellscore.kotlinexample.ui

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.maxwellscore.kotlinexample.LocationWorker
import com.maxwellscore.kotlinexample.R
import com.maxwellscore.kotlinexample.di.AppComponent
import com.maxwellscore.kotlinexample.domain.WeatherInteractor
import com.maxwellscore.kotlinexample.domain.entities.WeatherType
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: WeatherInteractor
) : ViewModel() {

    companion object {
        private const val CITY_MASK = "{city}"
    }

    private val _liveData: MutableLiveData<WeatherUiState> = MutableLiveData<WeatherUiState>()
    val liveData: LiveData<WeatherUiState> = _liveData

    /**
     * Метод вызывается когда activity завершит подготовительные работы. Затем метод идет в интерактор за данными,
     * которые потом преобразуются в данные ui слоя, которые удобно устанавливать для экрана
     */
    fun onInitiallyCreated(component: AppComponent, context: Context) {
        component.inject(this)
        updateWeather(context)
    }

    fun onServiceButtonClicked(context: Context) {
        WorkManager.getInstance(context)
            .beginUniqueWork(
                "SuperDuperLocationSender",
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(LocationWorker::class.java)
            )
            .enqueue()
    }

    fun onScreenClicked(context: Context) {
        updateWeather(context)
    }

    private fun updateWeather(context: Context) {
        interactor.getWeather()
            .map { weather ->
                val title =
                    context.getString(R.string.weather_in_mask).replace(CITY_MASK, weather.city)
                val (weatherTextResId, backgroundColorId) = getWeatherTypeTextAndColorId(weather.weatherType)
                WeatherUiState(
                    title,
                    weather.temperature,
                    weatherTextResId,
                    ContextCompat.getColor(context, backgroundColorId)
                )
            }
            .subscribeOn(Schedulers.io())
            .subscribe(_liveData::postValue)
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

    class Factory @Inject constructor(private val viewModel: MainViewModel) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }
}