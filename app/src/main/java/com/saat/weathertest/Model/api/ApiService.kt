package com.saat.weathertest.Model.api

import com.saat.weathertest.Model.Common
import com.saat.weathertest.Model.dataModel.CitiesWeatherModel
import retrofit2.Call

class ApiService(var apiClient: ApiClient){

    fun getWeather(lat:String , lon:String):Call<CitiesWeatherModel>{
        return apiClient.getDaily(lat,lon,Common.API_UNIT,Common.KEY)
    }
}