package com.maxwellscore.kotlinexample.di

import android.content.Context
import com.maxwellscore.kotlinexample.ui.MainActivity
import com.maxwellscore.kotlinexample.ui.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(viewModel: MainViewModel)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}
