package com.saat.weathertest.dagger.module

import android.app.Application
import com.google.gson.Gson
import com.saat.weathertest.Model.api.ApiService
import com.saat.weathertest.Model.repository.Repository
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class, ApiModule::class])
class RepositoryModule{

    @Provides
    fun repositoryProvide(application: Application , gson: Gson ,apiService: ApiService) : Repository{

        return Repository(application,gson,apiService);
    }

}
