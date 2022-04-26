package com.maxwellscore.kotlinexample.di

import android.content.Context
import com.maxwellscore.kotlinexample.domain.WeatherInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesWeatherInteractor(context: Context): WeatherInteractor {
        return WeatherInteractor(context)
    }
}
