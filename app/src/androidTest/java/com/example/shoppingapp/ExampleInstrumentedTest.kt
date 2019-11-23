package com.example.shoppingapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import com.example.shoppingapp.data.dao.ShoppingDao
import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.entities.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
class ExampleInstrumentedTest {
    lateinit var database: ShoppingDatabase
    lateinit var shoppingDao: ShoppingDao
    @Before
    @Throws(IOException::class)
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, ShoppingDatabase::class.java).build()
        shoppingDao = database.ShoppingDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun addUser() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            shoppingDao.insertItem(
                Item(
                    "test",
                    "hoodie",
                    "12,4",
                    "drawable::packageName/R.id.drawable",
                    1
                )
            )
        }
        job.join()
        val getItem = withContext(Dispatchers.IO) {shoppingDao.getData()}
        assertThat(getItem.value?.get(0)?.type, equalTo("hoodie"))
    }

}
