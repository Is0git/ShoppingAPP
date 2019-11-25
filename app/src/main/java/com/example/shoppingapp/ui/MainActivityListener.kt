package com.example.shoppingapp.ui

import com.example.shoppingapp.data.entities.FlattenItem

interface MainActivityListener {

    fun deleteItemOnClick(item: FlattenItem)
}