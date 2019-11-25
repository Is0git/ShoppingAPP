package com.example.shoppingapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.entities.FlattenItem
import com.example.shoppingapp.di.activity.ActivityScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor(val repo: MainActivityRepository) : ViewModel() {

    var myItems = repo.getMyItems()

    fun addMyItem(id: String) = viewModelScope.launch {
        repo.addMyItem(id)
    }

    fun deleteAllMyItems() {
        viewModelScope.launch { repo.deleteAll() }
    }

    fun deleteItem(item: FlattenItem) = viewModelScope.launch { repo.deleteItem(item) }

}