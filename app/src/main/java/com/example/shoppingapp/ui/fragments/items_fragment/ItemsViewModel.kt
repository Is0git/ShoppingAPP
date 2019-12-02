package com.example.shoppingapp.ui.fragments.items_fragment

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.di.activity.items_fragment.ItemsScope
import javax.inject.Inject

@ItemsScope
class ItemsViewModel @Inject constructor(val repo: ItemsRepository) : ViewModel() {

    fun getItems(typeID: Int) = repo.getParticularItems(typeID)
}