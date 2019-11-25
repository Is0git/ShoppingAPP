package com.example.shoppingapp.ui

import android.animation.ObjectAnimator
import android.graphics.ColorFilter
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.data.entities.FlattenItem
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.util.databinding.PricePlaceHolder
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), View.OnDragListener, MotionLayout.TransitionListener, MainActivityListener {
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var adapter: CardItemsList
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
            binding.dragView.setOnDragListener(this@MainActivity)
            cardItemsList.adapter = adapter.also { it.listener = this@MainActivity }
            constraintLayout.setTransitionListener(this@MainActivity)
            deleteButton.setOnClickListener { viewModel.deleteAllMyItems() }
            lifecycleOwner = this@MainActivity
            vwmdl = viewModel

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = true
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val item = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteItem(item)
                }

            }).attachToRecyclerView(binding.cardItemsList)
        }
        viewModel.myItems.observe(this@MainActivity, Observer {
            adapter.submitList(it)
            binding.apply {
                val mappedToDouble = it.map { it.price }
                lifecycleScope.launch { PricePlaceHolder.getFullPrice(itemsSum, mappedToDouble) }
                itemsCount.text = it.size.toString()
            }

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
                if (event.result) {
                    binding.root.background = getDrawable(R.color.colorPrimary)
                    flickAnimation()
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

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        alphaAnimation(0f)
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
        if (p1 == R.id.end) {
            (binding.cardItemsList.layoutManager as LinearLayoutManager).orientation =
                RecyclerView.VERTICAL
            alphaAnimation(1f)
        }

        if (p1 == R.id.start) {
            (binding.cardItemsList.layoutManager as LinearLayoutManager).orientation =
                RecyclerView.HORIZONTAL
            alphaAnimation(1f)
        }
    }

    fun alphaAnimation(value: Float) {
        ObjectAnimator.ofFloat(binding.cardItemsList, "alpha", value).apply {
            duration = 250
        }.start()

    }

    fun flickAnimation() {
        ObjectAnimator.ofFloat(binding.circleImageView, "alpha", 0f, 1f).apply {
            duration = 800
        }.start()
    }

    override fun deleteItemOnClick(item: FlattenItem) {
        viewModel.deleteItem(item)
    }



}
