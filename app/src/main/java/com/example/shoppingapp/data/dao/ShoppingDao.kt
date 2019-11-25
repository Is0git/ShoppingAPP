package com.example.shoppingapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingapp.data.entities.FlattenItem
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.data.entities.MyItems

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM item_table")
    fun getData() : LiveData<List<Item>>

    @Query("SELECT * FROM item_table WHERE typeId == :type")
    fun getCertainType(type: Int) : LiveData<List<Item>>

    @Insert
    suspend fun insertItem(item: Item)

    @Insert
    suspend fun myItem(myItems: MyItems)

    @Query("SELECT * FROM my_items INNER JOIN item_table ON my_items.item_id = item_table.id")
    fun getMyItems() : LiveData<List<FlattenItem>>
}