package com.example.shoppingapp.ui.fragments.full_item_fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.R
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.databinding.FullItemFragmentBinding
import com.example.shoppingapp.ui.MainActivity
import dagger.android.support.DaggerFragment

class FullItemFragment : DaggerFragment() {
    lateinit var binding: FullItemFragmentBinding
    lateinit var navController: NavController
    val args: FullItemFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FullItemFragmentBinding.inflate(inflater, container, false).apply {
            item = args.item as Item
            bodyCard.setOnClickListener { navController.navigateUp() }
            addItem.setOnClickListener {
                (activity!! as MainActivity).apply {
                    viewModel.addMyItem((args.item as Item).id.toString())
                    flickAnimation()
                    navController.navigateUp()
                }
            }
        }
        setTransitionNames()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
    override fun onStart() {
        super.onStart()
        (activity!! as MainActivity).hideAppBar()
    }

    fun setSharedTransition() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move)
    }

    fun setTransitionNames() {
        binding.itemCard.transitionName = args.transitionName
    }
}