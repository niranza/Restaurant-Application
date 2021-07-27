package com.niran.restaurantapplication.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    val itemId: Int = 0,

    @ColumnInfo(name = "item_name")
    val itemName: String = "",

    @ColumnInfo(name = "item_type")
    val itemType: Int = 0,

    @ColumnInfo(name = "item_price")
    val itemPrice: Double = -1.0,

    @ColumnInfo(name = "item_image_id")
    val itemImageId: Int = -1,

    @ColumnInfo(name = "item_quantity")
    val itemQuantity: Int = 0,

    @ColumnInfo(name = "item_ingredients")
    val itemIngredients: Ingredients = Ingredients(),

    /*@ColumnInfo(name = "item_tags")
    val itemTag: List<Tags>,*/
)
