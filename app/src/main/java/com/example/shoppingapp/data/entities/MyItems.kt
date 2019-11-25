package com.example.shoppingapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_items")
data class MyItems(val item_id: Int, @PrimaryKey(autoGenerate = true) val my_id:Int = 0)