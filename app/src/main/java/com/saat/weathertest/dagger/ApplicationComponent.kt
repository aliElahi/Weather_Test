 package com.saat.weathertest.dagger

import android.app.Application
import com.saat.weathertest.dagger.module.*
import com.saat.weathertest.view.MainActivity
import dagger.Component


@Component(modules = [
    ApplicationModule::class,
    RepositoryModule::class,
    ViewModelFactoryModule::class ,
    ViewModule::class])
interface ApplicationComponent {

    fun application():Application

    fun inject(mainActivity: MainActivity)
}