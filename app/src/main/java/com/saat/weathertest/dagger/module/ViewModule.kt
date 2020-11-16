package com.saat.weathertest.dagger.module

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saat.weathertest.view.adapters.DailyAdapter
import com.saat.weathertest.view.adapters.HourlyAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ApplicationModule::class])
class ViewModule{

    @Provides()
    @Named("horizontal")
    fun linearLayoutManagerHorizontalProvide(application: Application):LinearLayoutManager {
        return LinearLayoutManager(application,LinearLayoutManager.HORIZONTAL,false)
    }

    @Provides()
    @Named("vertical")
    fun linearLayoutManagerVerticalProvide(application: Application):LinearLayoutManager {
        return  LinearLayoutManager(application,LinearLayoutManager.VERTICAL,false)
    }

    @Provides
    fun hourlyAdapterProvide() = HourlyAdapter()

    @Provides
    fun dailyAdapterProvide() = DailyAdapter()

}