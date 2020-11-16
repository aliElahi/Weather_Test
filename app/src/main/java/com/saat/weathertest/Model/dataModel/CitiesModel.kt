package com.saat.weathertest.Model.dataModel

import ir.mirrajabi.searchdialog.core.Searchable

class CitiesModel : ArrayList<CitiesModelItem>()

class CitiesModelItem constructor(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val state: String
):Searchable {
    override fun getTitle(): String {
        return name
    }

}

data class Coord(
    val lat: Double,
    val lon: Double
)

class XX : Searchable{
    override fun getTitle(): String {
        TODO("Not yet implemented")
    }

}