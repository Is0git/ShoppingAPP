package com.example.shoppingapp.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "item_table")
@Parcelize
data class Item(
    val title: String?,
    val type: String?,
    val typeId: Int?,
    val price: Double?,
    val imageUri: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
}