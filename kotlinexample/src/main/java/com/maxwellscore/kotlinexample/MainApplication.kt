package com.maxwellscore.kotlinexample

import android.app.Application
import com.maxwellscore.kotlinexample.di.AppComponent
import com.maxwellscore.kotlinexample.di.DaggerAppComponent

class MainApplication : Application(), AppComponentProvider {

    private var _component: AppComponent? = null
    override val component: AppComponent
        get() = _component!!

    override fun onCreate() {
        super.onCreate()
        _component = DaggerAppComponent.factory().create(this)
    }
}
