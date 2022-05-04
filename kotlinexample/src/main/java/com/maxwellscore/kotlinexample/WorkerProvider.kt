package com.maxwellscore.kotlinexample

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.maxwellscore.kotlinexample.di.AppComponent
import com.maxwellscore.kotlinexample.di.DaggerWorkerComponent

class WorkerProvider constructor(private val appComponent: AppComponent) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val workerComponent = DaggerWorkerComponent.factory().create(appComponent, workerParameters, appContext)

        return when(workerClassName) {
            LocationWorker::class.qualifiedName -> workerComponent.locationWorker()
            else -> null
        }
    }
}