package com.example.shoppingapp.di.activity

import com.example.shoppingapp.di.activity.home_fragment.HomeFragmentScope
import com.example.shoppingapp.di.activity.items_fragment.ItemViewModelModule
import com.example.shoppingapp.di.activity.items_fragment.ItemsScope
import com.example.shoppingapp.ui.fragments.home_fragment.HomeFragment
import com.example.shoppingapp.ui.fragments.items_fragment.ItemsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    @HomeFragmentScope
    abstract fun getHomeFragment() : HomeFragment

    @ContributesAndroidInjector(modules = [ItemViewModelModule::class])
    @ItemsScope
    abstract fun getItemsFragment() : ItemsFragment

}