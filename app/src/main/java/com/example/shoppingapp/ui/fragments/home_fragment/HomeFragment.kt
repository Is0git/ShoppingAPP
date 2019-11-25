package com.example.shoppingapp.ui.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.shoppingapp.data.models.Categories
import com.example.shoppingapp.databinding.HomeFragmentBinding
import com.example.shoppingapp.util.contracts.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment(), OnCategoryListener {

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
        binding.itemRecyclerView.adapter = adapter.also {
            it.listener = this
            setAdapterData()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    fun setAdapterData() {
        adapter.submitList(
            listOf(
                Categories(
                    HOODIES,
                    "Hoodies",
                    "android.resource://${activity?.packageName}/drawable/hoodie".toUri(),
                    "ERER"
                ),
                Categories(
                    COATS,
                    "Coats",
                    "android.resource://${activity?.packageName}/drawable/coat".toUri(),
                    "ERER"
                ),
                Categories(
                    DRESS_PANTS,
                    "Dress pants",
                    "android.resource://${activity?.packageName}/drawable/dress_pants".toUri(),
                    "ERER"
                ),
                Categories(
                    JACKETS,
                    "Jackets",
                    "android.resource://${activity?.packageName}/drawable/jacket".toUri(),
                    "ERER"
                ),
                Categories(
                    Jeans,
                    "Jeans",
                    "android.resource://${activity?.packageName}/drawable/jeans".toUri(),
                    "ERER"
                ),
                Categories(
                    LONG_SLEEVE_JERSEYS,
                    "Long sleeve jerseys",
                    "android.resource://${activity?.packageName}/drawable/long_sleeve_jersey".toUri(),
                    "ERER"
                ),
                Categories(
                    LONG_SLEEVE_TOP,
                    "Long sleeve top",
                    "android.resource://${activity?.packageName}/drawable/long_sleeve_top".toUri(),
                    "ERER"
                ),
                Categories(
                    SHORTS,
                    "Shorts",
                    "android.resource://${activity?.packageName}/drawable/shorts".toUri(),
                    "ERER"
                ),
                Categories(
                    SWEATER,
                    "Sweater",
                    "android.resource://${activity?.packageName}/drawable/sweather".toUri(),
                    "ERER"
                ),
                Categories(
                    TANK_TOP,
                    "Tank top",
                    "android.resource://${activity?.packageName}/drawable/tank_top".toUri(),
                    "ERER"
                ),
                Categories(
                    VEST,
                    "Vest",
                    "android.resource://${activity?.packageName}/drawable/vest".toUri(),
                    "ERER"
                )
            )
        )
    }
    override fun onCategoryClick(typeID: Int) {
        val direction = HomeFragmentDirections.actionHomeFragmentToItemsFragment(typeID)
        navController.navigate(direction)
    }
}