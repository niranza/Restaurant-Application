package com.niran.restaurantapplication.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starter_table")
data class Starter(

    @PrimaryKey(autoGenerate = false)
    val starterId: Int = 1,

    @ColumnInfo(name = "is_first_time")
    val isFirstTime: Boolean = true
)
