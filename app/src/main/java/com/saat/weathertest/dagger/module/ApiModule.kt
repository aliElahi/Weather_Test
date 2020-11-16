package com.saat.weathertest.dagger.module

import com.google.gson.Gson
import com.saat.weathertest.Model.Common
import com.saat.weathertest.Model.api.ApiClient
import com.saat.weathertest.Model.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule{


    @Provides
    fun apiServiceProvide(retrofit: Retrofit):ApiService{
        return ApiService(retrofit.create(ApiClient::class.java))
    }


    @Provides
    fun apiRetrofitProvide(gsonConverterFactory: GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Common.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun GsonConverterFactoryProvide() = GsonConverterFactory.create()

    @Provides
    fun gson() = Gson()
}