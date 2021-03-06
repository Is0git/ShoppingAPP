package com.example.shoppingapp.di.activity

import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    @ActivityScope
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}