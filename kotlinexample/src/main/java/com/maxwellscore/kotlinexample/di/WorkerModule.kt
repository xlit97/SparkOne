package com.maxwellscore.kotlinexample.di

import android.content.Context
import androidx.work.WorkerParameters
import com.maxwellscore.kotlinexample.LocationWorker
import com.maxwellscore.kotlinexample.MyLocationManager
import dagger.Module
import dagger.Provides

@Module
class WorkerModule {

    @Provides
    fun providesLocationWorker(context: Context, workerParameters: WorkerParameters, myLocationManager: MyLocationManager): LocationWorker = LocationWorker(
        appContext = context,
        workerParams = workerParameters,
        myLocationManager = myLocationManager,
    )
}