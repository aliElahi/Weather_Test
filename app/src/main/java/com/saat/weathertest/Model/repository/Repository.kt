package com.saat.weathertest.Model.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.saat.weathertest.Model.Common
import com.saat.weathertest.Model.api.ApiService
import com.saat.weathertest.Model.assetFile.Assets
import com.saat.weathertest.Model.dataModel.CitiesModelItem
import com.saat.weathertest.Model.dataModel.CitiesWeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    application: Application,
    gson: Gson,
    var apiService: ApiService
) {

    private companion object val TAG = "Repository"

    var listOfCities : List<CitiesModelItem>
    private set

    var weatherLiveData: MutableLiveData<CitiesWeatherModel>
    private set

    var errorLiveData : MutableLiveData<String>
    private set

    init {
        this.listOfCities = Assets(application,gson, Common.JSON_CITIES_FILE_NAME).getListOfCities()!!
        this.weatherLiveData = MutableLiveData()
        this.errorLiveData = MutableLiveData()
    }

   fun getWeather(lat:String , lon:String){
       apiService
               .getWeather(lat,lon)
               .enqueue(object : Callback<CitiesWeatherModel> {
                   override fun onResponse(call: Call<CitiesWeatherModel>, response: Response<CitiesWeatherModel>) {
                       if (response.isSuccessful) {
                           weatherLiveData.postValue(response.body())
                       } else {

                           Log.d(TAG, "onResponse: request response not successful -- Error code : "
                                   + response.code())
                       }

                   }

                   override fun onFailure(call: Call<CitiesWeatherModel>, t: Throwable) {

                   }
               })
   }


}