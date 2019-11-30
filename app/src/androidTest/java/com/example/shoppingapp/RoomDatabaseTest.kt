package com.example.shoppingapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shoppingapp.data.dao.ShoppingDao
import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.data.entities.MyItems
import com.example.shoppingapp.ui.MainActivityRepository
import com.example.shoppingapp.ui.MainActivityViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//database is good enough for testing on emulator because I don't have to launch activities
@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {

    lateinit var viewModel: MainActivityViewModel
    lateinit var database: ShoppingDatabase
    lateinit var dao: ShoppingDao


    @Before
    fun setupViewModel() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, ShoppingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        viewModel = MainActivityViewModel(MainActivityRepository((database)))
        dao = database.shoppingDao()
    }

    @Test
    fun localItemsInsertion() {
        runBlocking {
            dao.insertItem(Item("SDSd", "sdsd", 1, 1.0, "sdsd"))
            val items = dao.getAllItems()
            Assert.assertEquals(1, items.size)
        }
    }

    @Test
    fun myItemInsertion() {
        runBlocking {
            dao.myItem(MyItems(3))
            val items = dao.getMyAllItems()
            Assert.assertEquals(1, items.size)
            Assert.assertEquals(items.firstOrNull()?.item_id, 3)
        }
    }

    @Test
    fun allItemsDelete() {
        runBlocking {
            dao.myItem(MyItems(3))
            dao.myItem(MyItems(4))
            var items = dao.getMyAllItems()
            Assert.assertEquals(2, items.size)
            dao.deleteItems()
            items = dao.getMyAllItems()
            Assert.assertEquals(0, items.size)
        }
    }

    @Test
    fun singleItemDelete() {
        runBlocking {
            dao.myItem(MyItems(3))
            dao.myItem(MyItems(4))
            var items = dao.getMyAllItems()
            Assert.assertEquals(2, items.size)
            dao.deleteItem(4)
            items = dao.getMyAllItems()
            Assert.assertEquals(1, items.size)
            Assert.assertEquals(items.firstOrNull()?.item_id, 3)
        }

    }

    @After
    fun closeDb() {
        database.close()
    }
}