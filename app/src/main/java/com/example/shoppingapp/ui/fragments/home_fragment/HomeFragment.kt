package com.example.shoppingapp.ui.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.shoppingapp.data.models.Categories
import com.example.shoppingapp.databinding.HomeFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    lateinit var navController: NavController
    lateinit var binding: HomeFragmentBinding
    @Inject
    lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.itemRecyclerView.adapter = adapter.also { setAdapterData() }


        return binding.root
    }

    fun setAdapterData(){
        adapter.submitList(
            listOf(
                Categories(1, "Hoodies", "android.resource://${activity?.packageName}/drawable/hoodie".toUri(), "ERER"),
                Categories(2, "Coats", "android.resource://${activity?.packageName}/drawable/coat".toUri(), "ERER"),
                Categories(3, "Dress pants", "android.resource://${activity?.packageName}/drawable/dress_pants".toUri(), "ERER"),
                Categories(4, "Jackets", "android.resource://${activity?.packageName}/drawable/jacket".toUri(), "ERER"),
                Categories(5, "Jeans", "android.resource://${activity?.packageName}/drawable/jeans".toUri(), "ERER"),
                Categories(6, "Long sleeve jerseys", "android.resource://${activity?.packageName}/drawable/long_sleeve_jersey".toUri(), "ERER"),
                Categories(7, "Long sleeve top", "android.resource://${activity?.packageName}/drawable/long_sleeve_top".toUri(), "ERER"),
                Categories(8, "Shorts", "android.resource://${activity?.packageName}/drawable/shorts".toUri(), "ERER"),
                Categories(9, "Sweater", "android.resource://${activity?.packageName}/drawable/sweather".toUri(), "ERER"),
                Categories(10, "Tank top", "android.resource://${activity?.packageName}/drawable/tank_top".toUri(), "ERER"),
                Categories(11, "Vest", "android.resource://${activity?.packageName}/drawable/vest".toUri(), "ERER")
            )
        )
    }
}