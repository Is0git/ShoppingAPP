package com.example.shoppingapp.data.models

import androidx.room.PrimaryKey

data class FlattenItem(
    val my_id: Int, val item_id: Int, val title: String?,
    val type: String?,
    val typeId: Int?,
    val price: Double?,
    val imageUri: String?,
    val id: Int
)