package com.example.shoppingapp.ui

import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.data.models.FlattenItem
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.ui.fragments.home_fragment.HomeFragment
import com.example.shoppingapp.ui.fragments.items_fragment.*
import com.example.shoppingapp.util.databinding.PricePlaceHolder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), View.OnDragListener,
    MotionLayout.TransitionListener, MainActivityListener, PopupMenu.OnMenuItemClickListener,
    DialogInterface.OnClickListener {
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var adapter: CardItemsList
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var navController: NavController
    lateinit var searchView: androidx.appcompat.widget.SearchView
    var job: Job? = Job()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.main_fragment_container)
        setSupportActionBar(binding.toolbar)

        setupBinding()
        recyclerViewItemTouchHelper()
        setObservers()
    }

    fun setObservers() {
        viewModel.myItems.observe(this@MainActivity, Observer {
            adapter.submitList(it)
            binding.apply {
                val mappedToDouble = it.map { it.price }
                lifecycleScope.launch { PricePlaceHolder.getFullPrice(itemsSum, mappedToDouble) }
                itemsCount.text = it.size.toString()
            }

        })
    }


    fun setupBinding() {
        binding.apply {
            toolbar.setupWithNavController(navController)
            binding.dragView.setOnDragListener(this@MainActivity)
            cardItemsList.adapter = adapter.also { it.listener = this@MainActivity }
            constraintLayout.setTransitionListener(this@MainActivity)
            deleteButton.setOnClickListener { buildDeleteDialog() }
            lifecycleOwner = this@MainActivity
            vwmdl = viewModel
        }
    }

    fun recyclerViewItemTouchHelper() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menu?.findItem(R.id.filter)?.isVisible = false
        searchView =
            menu?.findItem(R.id.action_search)?.actionView as androidx.appcompat.widget.SearchView
        searchHandle()
        return true
    }

    fun buildDeleteDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("REMOVE ALL ITEMS?")
            .setMessage("Doing so will permanently delete all items from your chart")
            .setPositiveButton("Ok", this)
            .setNegativeButton("CANCEL", null)
            .show()
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


    fun searchHandle() {
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)
            val homeFragment: HomeFragment? =
                fragment?.childFragmentManager?.fragments?.get(0) as HomeFragment

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                homeFragment?.adapter?.let {
                    if (job?.isActive!!) job?.cancel()
                    job = lifecycleScope.launch {
                        it.filter(newText)
                        homeFragment.binding.itemRecyclerView.smoothScrollToPosition(0)
                    }
                }
                return true

            }

        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter) {
            val v = findViewById<View>(R.id.filter)
            val popup = PopupMenu(this, v)
            popup.apply {
                menuInflater.inflate(R.menu.items_menu, popup.menu)
                setOnMenuItemClickListener(this@MainActivity)
                show()
            }
        }
        return true
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

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        item?.isChecked = true
        val itemsFragment: ItemsFragment? =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container)?.childFragmentManager?.fragments?.get(
                0
            ) as ItemsFragment
        lifecycleScope.launch {
            when (item?.itemId) {
                R.id.byPriceLowest -> itemsFragment?.adapter?.filter(BY_PRICE_FROM_LOWEST)
                R.id.byPriceHighest -> itemsFragment?.adapter?.filter(BY_PRICE_FROM_HIGHEST)
                R.id.byName -> itemsFragment?.adapter?.filter(BY_NAME)
                R.id.byType -> itemsFragment?.adapter?.filter(BY_TYPE)
            }
            itemsFragment?.binding?.itemRecyclerView?.smoothScrollToPosition(0)
        }

        return true
    }

    fun hideAppBar() {
        binding.appbarLayout.apply {
            if (visibility == View.VISIBLE) visibility = View.INVISIBLE
        }
    }

    fun showAppBar() {
        binding.appbarLayout.apply {
            if (visibility == View.INVISIBLE) visibility = View.VISIBLE
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        viewModel.deleteAllMyItems()
    }
}
