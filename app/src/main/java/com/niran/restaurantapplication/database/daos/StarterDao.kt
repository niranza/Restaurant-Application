package com.niran.restaurantapplication.database.daos

import androidx.room.*
import com.niran.restaurantapplication.database.models.Starter

@Dao
interface StarterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarter(starter: Starter)

    @Update
    suspend fun updateStarter(starter: Starter)

    @Query("DELETE FROM starter_table")
    suspend fun deleteStarter()

    @Query("SELECT * FROM starter_table")
    suspend fun getStarter(): Starter

}