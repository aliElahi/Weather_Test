package com.saat.weathertest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saat.weathertest.Model.repository.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor (private val repository: Repository)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            val viewModel = MainActivityViewModel(repository)
            return (viewModel as T)
        }
        else
            throw IllegalArgumentException("view model must be " + MainActivityViewModel::class.simpleName)
    }

}