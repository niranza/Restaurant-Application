package com.niran.restaurantapplication.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.niran.restaurantapplication.database.models.DataDate

@Dao
interface DataDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataDate(dataDate: DataDate)

    @Query("SELECT * FROM data_date_table")
    suspend fun getDataDate(): DataDate?

}