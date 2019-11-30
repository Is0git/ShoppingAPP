package com.example.shoppingapp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shoppingapp.data.dao.ShoppingDao
import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.entities.MyItems
import com.example.shoppingapp.data.models.FlattenItem
import com.example.shoppingapp.ui.MainActivityRepository
import com.example.shoppingapp.ui.MainActivityViewModel
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LiveDataTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: MainActivityViewModel
    private val observer: Observer<List<FlattenItem>> = mock()
    lateinit var databaseDao: ShoppingDao

    @Before
    fun setupData() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val database = Room.inMemoryDatabaseBuilder(context, ShoppingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val repository = MainActivityRepository(database)
        databaseDao = repository.database.shoppingDao()
        viewModel = MainActivityViewModel(repository)
        viewModel.myItems.observeForever(observer)
    }

    @Test
    fun isMyItemsObserveActive() = runBlocking {

        databaseDao.myItem(MyItems(5))
        Assert.assertEquals(1, viewModel.myItems.value?.size)


    }

    @After
    fun removeObserver() {
        viewModel.myItems.removeObserver {}
    }

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}