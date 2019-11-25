package com.example.shoppingapp.ui

import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.entities.FlattenItem
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.data.entities.MyItems
import com.example.shoppingapp.di.activity.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainActivityRepository @Inject constructor(val database: ShoppingDatabase) {

    private val dao = database.shoppingDao()

    fun getMyItems() = database.shoppingDao().getMyItems()

    suspend fun addMyItem(id: String) = dao.myItem(MyItems(id.toInt()))

    suspend fun deleteItem(item:FlattenItem) = dao.deleteItem(item.item_id)


    suspend fun deleteAll() = dao.deleteItems()


    suspend fun addMocks() {
        database.shoppingDao().insertItem(
            Item(
                "Red Hoodie with N attribute",
                "Hoodie",
                1,
                55.00,
                "android.resource://com.example.shoppingapp/drawable/hoodie10"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Blue Hoodie with N attribute",
                "Hoodie",
                1,
                40.00,
                "android.resource://com.example.shoppingapp/drawable/hoodie11"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Pink Hoodie with N attribute",
                "Hoodie",
                1,
                30.00,
                "android.resource://com.example.shoppingapp/drawable/hoodie12"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Grey Hoodie with N attribute",
                "Hoodie",
                1,
                20.0,
                "android.resource://com.example.shoppingapp/drawable/hoodie2"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Brown Hoodie with N attribute",
                "Hoodie",
                1,
                10.0,
                "android.resource://com.example.shoppingapp/drawable/hoodie3"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Coral Hoodie with N attribute",
                "Hoodie",
                1,
                40.00,
                "android.resource://com.example.shoppingapp/drawable/hoodie4"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Crimson Hoodie with N attribute",
                "Hoodie",
                1,
                70.05,
                "android.resource://com.example.shoppingapp/drawable/hoodie5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Black Hoodie with N attribute",
                "Hoodie",
                1,
                50.00,
                "android.resource://com.example.shoppingapp/drawable/hoodie6"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "SkyBlue Hoodie with N attribute",
                "Hoodie",
                1,
                60.0,
                "android.resource://com.example.shoppingapp/drawable/hoodie7"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Salmon Hoodie with N attribute",
                "Hoodie",
                1,
                40.0,
                "android.resource://com.example.shoppingapp/drawable/hoodie9"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                " Dark Coat with N attribute",
                "Coats",
                2,
                50.0,
                "android.resource://com.example.shoppingapp/drawable/coat2"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Red Coat with N attribute",
                "Coats",
                2,
                80.00,
                "android.resource://com.example.shoppingapp/drawable/coat5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Blue Coat with N attribute",
                "Coats",
                2,
                110.69,
                "android.resource://com.example.shoppingapp/drawable/coat3"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Grey Coat with N attribute",
                "Coats",
                2,
                120.10,
                "android.resource://com.example.shoppingapp/drawable/coat4"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Grey Dress Pants with N attribute",
                "Dress pants",
                3,
                72.99,
                "android.resource://com.example.shoppingapp/drawable/dress_pants2"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Black Dress Pants with N attribute",
                "Dress pants",
                3,
                66.19,
                "android.resource://com.example.shoppingapp/drawable/dress_pants3"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Red Dress Pants with N attribute",
                "Dress pants",
                3,
                55.19,
                "android.resource://com.example.shoppingapp/drawable/dress_pants4"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Green Dress Pants with N attribute",
                "Dress pants",
                3,
                19.19,
                "android.resource://com.example.shoppingapp/drawable/dress_pants5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Grey Jacket with N attribute",
                "Jackets",
                4,
                12.13,
                "android.resource://com.example.shoppingapp/drawable/jacket2"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Grey Jacket with N attribute",
                "Jackets",
                4,
                50.00,
                "android.resource://com.example.shoppingapp/drawable/jacket3"
            )
        )


        database.shoppingDao().insertItem(
            Item(
                "Grey Jacket with N attribute",
                "Jackets",
                4,
                60.12,
                "android.resource://com.example.shoppingapp/drawable/jacket5"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Grey Jacket with N attribute",
                "Jackets",
                4,
                70.45,
                "android.resource://com.example.shoppingapp/drawable/jacket6"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Grey Jeans with N attribute",
                "Jeans",
                5,
                70.19,
                "android.resource://com.example.shoppingapp/drawable/jeans2"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Grey Jeans with N attribute",
                "Jeans",
                5,
                100.12,
                "android.resource://com.example.shoppingapp/drawable/jeans4"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Grey Jeans with N attribute",
                "Jeans",
                5,
                90.00,
                "android.resource://com.example.shoppingapp/drawable/jeans5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Grey Jeans with N attribute",
                "Jeans",
                5,
                65.55,
                "android.resource://com.example.shoppingapp/drawable/jeans6"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Grey Jeans with N attribute",
                "Jeans",
                5,
                40.48,
                "android.resource://com.example.shoppingapp/drawable/jeans7"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Jersey with n attribute",
                "Long Sleeve jerseys",
                6,
                48.48,
                "android.resource://com.example.shoppingapp/drawable/long_sleeve_jersey"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Top with n attribute",
                "Long sleeves top",
                7,
                49.49,
                "android.resource://com.example.shoppingapp/drawable/long_sleeve_top"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Shorts with n attribute",
                "Shorts",
                8,
                15.99,
                "android.resource://com.example.shoppingapp/drawable/shorts"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Just a sweater",
                "Sweaters",
                9,
                59.59,
                "android.resource://com.example.shoppingapp/drawable/sweather"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Tank top",
                "Tank tops",
                10,
                10.16,
                "android.resource://com.example.shoppingapp/drawable/tank_top"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Vests in 2019 XD",
                "Vests",
                11,
                31.56,
                "android.resource://com.example.shoppingapp/drawable/vest"
            )
        )
    }

}