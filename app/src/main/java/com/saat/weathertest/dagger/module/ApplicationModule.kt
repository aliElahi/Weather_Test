package com.saat.weathertest.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application:Application) {

    @Provides
    fun applicationProvider():Application{
        return application;
    }
}