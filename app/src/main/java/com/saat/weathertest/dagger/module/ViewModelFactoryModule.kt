package com.saat.weathertest.dagger.module

import com.saat.weathertest.Model.repository.Repository
import com.saat.weathertest.viewModel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class] )
class ViewModelFactoryModule{

    @Provides
    fun viewModelFactoryProvide(repository: Repository):ViewModelFactory{
        return ViewModelFactory(repository)
    }
}