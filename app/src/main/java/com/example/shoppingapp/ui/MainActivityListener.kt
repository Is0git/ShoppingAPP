package com.example.shoppingapp.ui

import com.example.shoppingapp.data.models.FlattenItem

interface MainActivityListener {

    fun deleteItemOnClick(item: FlattenItem)
}