package com.maxwellscore.kotlinexample

import android.app.Application
import androidx.work.Configuration
import com.maxwellscore.kotlinexample.di.AppComponent
import com.maxwellscore.kotlinexample.di.DaggerAppComponent

class MainApplication : Application(), AppComponentProvider, Configuration.Provider {

    private var _component: AppComponent? = null
    override val component: AppComponent
        get() = _component!!

    override fun onCreate() {
        super.onCreate()
        _component = DaggerAppComponent.factory().create(this)
        component.inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(WorkerProvider(component))
            .build()

}
