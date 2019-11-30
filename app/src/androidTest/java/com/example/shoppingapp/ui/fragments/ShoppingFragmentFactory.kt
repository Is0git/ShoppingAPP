package com.example.shoppingapp.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.shoppingapp.ui.fragments.home_fragment.HomeFragment

class ShoppingFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {
            HomeFragment::class.java.name -> {
                HomeFragment()
            }

            else -> super.instantiate(classLoader, className)


        }


}