package com.niran.restaurantapplication.database.daos

import androidx.room.*
import com.niran.restaurantapplication.database.models.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM item_table WHERE item_type = :type")
    fun getItemsByType(type: Int): Flow<List<Item>>

    @Query("DELETE FROM item_table")
    suspend fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item_table")
    fun getAllItems(): Flow<List<Item>>

}