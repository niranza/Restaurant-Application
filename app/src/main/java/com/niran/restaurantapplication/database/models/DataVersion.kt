package com.niran.restaurantapplication.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_version_table")
data class DataVersion(

    @PrimaryKey(autoGenerate = false)
    val dataVersionId: Int = 1,

    @ColumnInfo(name = "data_version")
    val dataVersion: Int = 0
)
