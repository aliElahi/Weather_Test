package com.saat.weathertest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saat.weathertest.Model.dataModel.CitiesModelItem
import com.saat.weathertest.Model.dataModel.CitiesWeatherModel
import com.saat.weathertest.Model.repository.Repository
import javax.inject.Inject

class MainActivityViewModel constructor ( private var repository: Repository)
    : ViewModel() {

    fun getWeatherLiveData() = repository.listOfCities

    fun newWeather(citiesModelItem: CitiesModelItem){
        repository.getWeather(citiesModelItem.coord.lat.toString(),citiesModelItem.coord.lon.toString())
    }

    fun getDataLiveData(): MutableLiveData<CitiesWeatherModel> {
        return repository.weatherLiveData
    }

    fun getWeather(lat:String , lon:String){
        repository.getWeather(lat,lon)
    }

}