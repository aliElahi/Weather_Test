package com.saat.weathertest

import android.app.Application
import com.saat.weathertest.dagger.ApplicationComponent
import com.saat.weathertest.dagger.DaggerApplicationComponent
import com.saat.weathertest.dagger.module.*

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .repositoryModule(RepositoryModule())
                .apiModule(ApiModule())
                .viewModelFactoryModule(ViewModelFactoryModule())
                .viewModule(ViewModule())
            .build()
    }
}