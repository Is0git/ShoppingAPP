package com.example.shoppingapp.di

import com.example.shoppingapp.di.activity.ActivityScope
import com.example.shoppingapp.di.activity.FragmentBuilder
import com.example.shoppingapp.di.activity.ViewModelFactoryModule
import com.example.shoppingapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder  {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class, ViewModelFactoryModule::class, ActivityViewModelModule::class])
    @ActivityScope
    abstract fun getActivity() : MainActivity
}