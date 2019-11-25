package com.example.shoppingapp.ui

import android.graphics.ColorFilter
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), View.OnDragListener {
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject  lateinit var adapter: CardItemsList
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.main_fragment_container)
        setSupportActionBar(binding.toolbar)
        binding.apply {
            toolbar.setupWithNavController(navController)
            cardItemsList.setOnDragListener(this@MainActivity)
            cardItemsList.adapter = adapter
        }
        viewModel.myItems.observe(this@MainActivity, Observer {
            adapter.submitList(it)
            binding.itemsCount.text = it.size.toString()
        })
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                Log.d("ItemAdapter", "STARTED")
                return true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                Log.d("ItemAdapter", "ENTER")
                binding.root.background = getDrawable(R.color.colorAccent)
                return true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {
                Log.d("ItemAdapter", "LOCAITON")
                return true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                Log.d("ItemAdapter", "EXITED")
                binding.root.background = getDrawable(R.color.colorPrimary)
                return true
            }
            DragEvent.ACTION_DROP -> {
                Log.d("ItemAdapter", "DROP ${event.clipData.getItemAt(0).text}")
                viewModel.addMyItem(event.clipData.getItemAt(0).text.toString())
                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                if(event.result) {
                    binding.root.background = getDrawable(R.color.colorPrimary)
                    return true
                }
                return true
            }
            else -> {
                Log.d("ItemAdapter", "??")
                return false
            }
        }
    }


}
