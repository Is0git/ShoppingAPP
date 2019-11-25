package com.example.shoppingapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    val title: String?,
    val type: String?,
    val typeId: Int?,
    val price: Double?,
    val imageUri: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
}