package com.example.shoppingapp.ui.fragments.items_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.di.activity.items_fragment.ItemsScope
import javax.inject.Inject

@ItemsScope
class ItemsRepository @Inject constructor(val database: ShoppingDatabase) {

    var items: LiveData<List<Item>> = MutableLiveData<List<Item>>()
    fun getParticularItems(typeID: Int) = database.shoppingDao().getCertainType(typeID)


}