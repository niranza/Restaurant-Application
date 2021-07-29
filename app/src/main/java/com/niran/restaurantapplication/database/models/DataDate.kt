package com.niran.restaurantapplication.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_date_table")
data class DataDate(

    @PrimaryKey(autoGenerate = false)
    val dataDateId: Int = 1,

    @ColumnInfo(name = "data_date")
    val dataDate: Long = 0L

)
