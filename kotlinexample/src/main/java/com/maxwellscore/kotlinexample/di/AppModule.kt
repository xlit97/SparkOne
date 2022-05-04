package com.maxwellscore.kotlinexample.di

import android.content.Context
import com.maxwellscore.kotlinexample.MyLocationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesMyLocationManager(context: Context): MyLocationManager {
        return MyLocationManager(context)
    }
}