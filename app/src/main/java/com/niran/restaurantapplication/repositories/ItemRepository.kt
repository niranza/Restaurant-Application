package com.niran.restaurantapplication.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.niran.restaurantapplication.database.api.ItemApiService
import com.niran.restaurantapplication.database.daos.ItemDao
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.utils.FoodTypes

class ItemRepository(
    private val itemDao: ItemDao,
    private val itemApiService: ItemApiService,
) {

    val foodList = itemDao.getItemsByType(FoodTypes.FOOD.ordinal)

    val beverageList = itemDao.getItemsByType(FoodTypes.BEVERAGE.ordinal)

    val orderedItemsList = itemDao.getAllOrderedItems()

    fun getItem(itemId: Int): LiveData<Item> = itemDao.getItem(itemId).asLiveData()

    suspend fun deleteOrderedItems() = itemDao.deleteAllOrderedItems()

    suspend fun updateItem(item: Item) = itemDao.updateItem(item)

    suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)

    suspend fun insertItem(item: Item) = itemDao.insertItem(item)

    suspend fun insertAllItems() {
        val itemList = itemApiService.getAllItems()
        itemDao.deleteAllItems()
        for (item in itemList) {
            itemDao.insertItem(item)
        }
    }
}