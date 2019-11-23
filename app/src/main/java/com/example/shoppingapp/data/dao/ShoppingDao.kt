package com.example.shoppingapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingapp.data.entities.FlattenItem
import com.example.shoppingapp.data.entities.Item

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM item_table")
    fun getData() : LiveData<List<Item>>

    @Query("SELECT * FROM item_table WHERE type == :type")
    fun getCertainType(type: String) : LiveData<List<Item>>

    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM my_items, item_table INNER JOIN my_items ON my_items.item_id = item_table.id")
    fun getMyItems() : LiveData<List<FlattenItem>>
}