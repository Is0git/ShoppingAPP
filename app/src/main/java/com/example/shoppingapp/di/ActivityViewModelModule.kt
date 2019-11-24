package com.example.shoppingapp.di

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.di.activity.ActivityScope
import com.example.shoppingapp.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {
    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun activityViewModel(viewModel: MainActivityViewModel) : ViewModel
}