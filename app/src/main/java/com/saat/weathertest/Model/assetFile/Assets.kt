package com.saat.weathertest.Model.assetFile

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saat.weathertest.Model.dataModel.CitiesModel
import com.saat.weathertest.Model.dataModel.CitiesModelItem
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.suspendCoroutine

class Assets(private val context:Context,private val gson: Gson, private val fileName:String){

    private fun getJsonString():String{

        val jsonString:String

        try {
            jsonString = context
                .assets
                .open(fileName)
                .bufferedReader()
                .use { bufferedReader -> bufferedReader.readText() }

        } catch (e: Exception) {
            e.printStackTrace()
            return "";
        }

        return jsonString

    }

    fun getListOfCities():List<CitiesModelItem>? {

        val type = object : TypeToken<CitiesModel>(){}.type
        return try {
            val list: CitiesModel = gson.fromJson(getJsonString(),type)
            val filter = list.filter {citiesModelItem -> citiesModelItem.country == "IR" }
            filter
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}