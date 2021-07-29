package com.niran.restaurantapplication.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.niran.restaurantapplication.database.models.DataVersion

@Dao
interface DataVersionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataVersion(dataVersion: DataVersion)

    @Query("SELECT * FROM data_version_table")
    suspend fun getDataVersion(): DataVersion?

}