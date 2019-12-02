package com.example.shoppingapp.di

import android.app.Application
import androidx.room.Room
import com.example.shoppingapp.data.database.ShoppingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomDatabaseModule {

    @JvmStatic
    @Provides
    @Singleton
    fun getDatabase(application: Application): ShoppingDatabase = Room.databaseBuilder(
        application,
        ShoppingDatabase::class.java,
        "shopping_database"
    ).fallbackToDestructiveMigration().build()

}