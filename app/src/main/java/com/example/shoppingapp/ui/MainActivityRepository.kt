package com.example.shoppingapp.ui

import com.example.shoppingapp.data.database.ShoppingDatabase
import com.example.shoppingapp.data.entities.Item
import com.example.shoppingapp.di.activity.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainActivityRepository @Inject constructor(val database: ShoppingDatabase) {

    suspend fun addMocks() {
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$55",
                "android.resource://com.example.shoppingapp/drawable/hoodie10"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$40",
                "android.resource://com.example.shoppingapp/drawable/hoodie11"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$30",
                "android.resource://com.example.shoppingapp/drawable/hoodie12"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$20",
                "android.resource://com.example.shoppingapp/drawable/hoodie2"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$10",
                "android.resource://com.example.shoppingapp/drawable/hoodie3"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$40",
                "android.resource://com.example.shoppingapp/drawable/hoodie4"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$70",
                "android.resource://com.example.shoppingapp/drawable/hoodie5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$50",
                "android.resource://com.example.shoppingapp/drawable/hoodie6"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$60",
                "android.resource://com.example.shoppingapp/drawable/hoodie7"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Hoodie",
                "Coats",
                1,
                "$40",
                "android.resource://com.example.shoppingapp/drawable/hoodie9"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Coat",
                "Coats",
                2,
                "$50",
                "android.resource://com.example.shoppingapp/drawable/coat2"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Coat",
                "Coats",
                2,
                "$80",
                "android.resource://com.example.shoppingapp/drawable/coat5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Coat",
                "Coats",
                2,
                "$110",
                "android.resource://com.example.shoppingapp/drawable/coat3"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Coat",
                "Coats",
                2,
                "$120",
                "android.resource://com.example.shoppingapp/drawable/coat4"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Dress pants",
                "Dress pants",
                3,
                "$72",
                "android.resource://com.example.shoppingapp/drawable/dress_pants2"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Dress pants",
                "Dress pants",
                3,
                "$66",
                "android.resource://com.example.shoppingapp/drawable/dress_pants3"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Dress pants",
                "Dress pants",
                3,
                "$55",
                "android.resource://com.example.shoppingapp/drawable/dress_pants4"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Dress pants",
                "Dress pants",
                3,
                "$50",
                "android.resource://com.example.shoppingapp/drawable/dress_pants5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Jacket",
                "Jackets",
                4,
                "$70",
                "android.resource://com.example.shoppingapp/drawable/jacket2"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Jacket",
                "Jackets",
                4,
                "$50",
                "android.resource://com.example.shoppingapp/drawable/jacket3"
            )
        )


        database.shoppingDao().insertItem(
            Item(
                "Jacket",
                "Jackets",
                4,
                "$60",
                "android.resource://com.example.shoppingapp/drawable/jacket5"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Jacket",
                "Jackets",
                4,
                "$70",
                "android.resource://com.example.shoppingapp/drawable/jacket6"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Jeans",
                "Jeans",
                5,
                "$70",
                "android.resource://com.example.shoppingapp/drawable/jeans2"
            )
        )

        database.shoppingDao().insertItem(
            Item(
                "Jeans",
                "Jeans",
                5,
                "$100",
                "android.resource://com.example.shoppingapp/drawable/jeans4"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Jeans",
                "Jeans",
                5,
                "$90",
                "android.resource://com.example.shoppingapp/drawable/jeans5"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Jeans",
                "Jeans",
                5,
                "$60",
                "android.resource://com.example.shoppingapp/drawable/jeans6"
            )
        )
        database.shoppingDao().insertItem(
            Item(
                "Jeans",
                "Jeans",
                5,
                "$50",
                "android.resource://com.example.shoppingapp/drawable/jeans7"
            )
        )
//        database.shoppingDao().insertItem(
//            Item(
//                "Long sleeve jerseys",
//                "Long Sleeve jerseys",
//                6,
//                "$50",
//                "android.resource://com.example.shoppingapp/drawable/long_sleeve_jersey"
//            )
//        )
//        database.shoppingDao().insertItem(
//            Item(
//                "Long sleeve top",
//                "Long sleeves top",
//                7,
//                "$40.50",
//                "android.resource://com.example.shoppingapp/drawable/long_sleeve_top"
//            )
//        )
//        database.shoppingDao().insertItem(
//            Item(
//                "Shorts",
//                "Shorts",
//                8,
//                "$15.99",
//                "android.resource://com.example.shoppingapp/drawable/shorts"
//            )
//        )
//        database.shoppingDao().insertItem(
//            Item(
//                "Sweater",
//                "Sweaters",
//                9,
//                "$59.99",
//                "android.resource://com.example.shoppingapp/drawable/sweather"
//            )
//        )
//        database.shoppingDao().insertItem(
//            Item(
//                "Tank top",
//                "Tank tops",
//                10,
//                "$10.16",
//                "android.resource://com.example.shoppingapp/drawable/tank_top"
//            )
//        )
//        database.shoppingDao().insertItem(
//            Item(
//                "Vest",
//                "Vests",
//                11,
//                "$31.56",
//                "android.resource://com.example.shoppingapp/drawable/vest"
//            )
//        )
    }

}