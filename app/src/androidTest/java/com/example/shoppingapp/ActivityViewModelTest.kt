package com.example.shoppingapp

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.shoppingapp.data.dao.ShoppingDao
import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.ui.MainActivityRepository
import com.example.shoppingapp.ui.MainActivityViewModel
import kotlinx.coroutines.runBlocking
import org.junit.*

class ActivityViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var viewModel: MainActivityViewModel
    lateinit var database: ShoppingDatabase
    lateinit var dao: ShoppingDao

    @Before
    fun setupViewModel() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, ShoppingDatabase::class.java).allowMainThreadQueries().build()
        viewModel = MainActivityViewModel(MainActivityRepository((database)))
        dao = database.shoppingDao()
    }

    @Test
    fun insertToDatabase() {
        runBlocking {
            (1..10).forEach { dao.insertItem(Item("SDSd", "sdsd", 1, 1.0, "sdsd")) }

            val result = viewModel.myItems.value

            Assert.assertEquals(10, result?.size)
        }

    }

    @After
    fun closeDb() {
        database.close()
    }
}