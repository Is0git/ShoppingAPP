package com.example.shoppingapp.ui.fragments.items_fragment

import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.databinding.ItemsFragmentBinding
import com.example.shoppingapp.ui.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ItemsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var adapter: ItemAdapter
    lateinit var itemsViewModel: ItemsViewModel
    lateinit var binding: ItemsFragmentBinding

    val args: ItemsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ItemsViewModel::class.java)
        binding = ItemsFragmentBinding.inflate(inflater, container, false).also {
            it.itemRecyclerView.adapter = adapter
        }
        itemsViewModel.getItems(args.typeID).observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
//        itemsViewModel.items.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
        return binding.root
    }



}