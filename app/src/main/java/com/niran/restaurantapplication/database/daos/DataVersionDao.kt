package com.niran.restaurantapplication.database.daos

import androidx.room.*
import com.niran.restaurantapplication.database.models.DataVersion

@Dao
interface DataVersionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataVersion(dataVersion: DataVersion)

    @Update
    suspend fun updateDataVersion(dataVersion: DataVersion)

    @Query("DELETE FROM data_version_table")
    suspend fun deleteDataVersion()

    @Query("SELECT * FROM data_version_table")
    suspend fun getDataVersion(): DataVersion?

}