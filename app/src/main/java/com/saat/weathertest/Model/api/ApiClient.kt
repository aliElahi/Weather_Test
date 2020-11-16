package com.saat.weathertest.Model.api

import com.saat.weathertest.Model.dataModel.CitiesWeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("onecall")
    fun getDaily(@Query("lat")lat:String
                 ,@Query("lon") lon:String
                 ,@Query("units") unit:String
                 ,@Query("appid") appId:String):Call<CitiesWeatherModel>
}