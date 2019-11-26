package com.example.shoppingapp.ui.fragments.items_fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.R
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.databinding.ItemsFragmentBinding
import com.example.shoppingapp.databinding.ItemsListBinding
import com.example.shoppingapp.ui.MainActivity
import com.example.shoppingapp.ui.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ItemsFragment : DaggerFragment(), ItemListener {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var adapter: ItemAdapter
    lateinit var itemsViewModel: ItemsViewModel
    lateinit var navController: NavController
    lateinit var binding: ItemsFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    val args: ItemsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ItemsViewModel::class.java)
        binding = ItemsFragmentBinding.inflate(inflater, container, false).also {
            it.itemRecyclerView.adapter = adapter.also { it.listener = this@ItemsFragment }
        }
        itemsViewModel.getItems(args.typeID).observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

//        itemsViewModel.items.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.toolbar.menu.findItem(R.id.filter).isVisible = true
        (activity!! as MainActivity).showAppBar()
    }

    override fun onItemClick(item: Item, binding: ItemsListBinding) {
        val transitionName = "${System.currentTimeMillis()}c"
        val extras = FragmentNavigatorExtras(binding.mainCard to transitionName).also { binding.mainCard.transitionName = transitionName }
        val destination = ItemsFragmentDirections.actionItemsFragmentToFullItemFragment(item, transitionName)
        navController.navigate(destination, extras)
    }


}