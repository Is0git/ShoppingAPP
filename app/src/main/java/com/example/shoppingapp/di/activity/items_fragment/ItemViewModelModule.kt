package com.example.shoppingapp.di.activity.items_fragment

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.di.ViewModelKey
import com.example.shoppingapp.ui.fragments.items_fragment.ItemsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ItemViewModelModule  {
    @Binds
    @IntoMap
    @ItemsScope
    @ViewModelKey(ItemsViewModel::class)
    abstract fun getViewModel(viewModel: ItemsViewModel): ViewModel
}