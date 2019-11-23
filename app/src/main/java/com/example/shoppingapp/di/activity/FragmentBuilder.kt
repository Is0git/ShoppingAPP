package com.example.shoppingapp.di.activity

import com.example.shoppingapp.di.activity.home_fragment.HomeFragmentScope
import com.example.shoppingapp.ui.fragments.home_fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    @HomeFragmentScope
    abstract fun getHomeFragment() : HomeFragment

}