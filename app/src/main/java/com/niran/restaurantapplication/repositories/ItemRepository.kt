package com.niran.restaurantapplication.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.niran.restaurantapplication.database.api.ItemApiService
import com.niran.restaurantapplication.database.daos.ItemDao
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.utils.FoodTypes
import com.niran.restaurantapplication.utils.LoadingHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ItemRepository(
    private val itemDao: ItemDao,
    private val itemApiService: ItemApiService
) {

    val foodList = itemDao.getItemsByType(FoodTypes.FOOD.ordinal)

    val beverageList = itemDao.getItemsByType(FoodTypes.BEVERAGE.ordinal)

    val orderedItemsList = itemDao.getAllOrderedItems()

    fun getItem(itemId: Int): LiveData<Item> = itemDao.getItem(itemId).asLiveData()

    suspend fun deleteOrderedItems() = itemDao.deleteAllOrderedItems()

    suspend fun updateItem(item: Item) = itemDao.updateItem(item)

    suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)

    suspend fun insertItem(item: Item) = itemDao.insertItem(item)

    fun insertAllItems(
        scope: CoroutineScope,
        loadingHandler: LoadingHandler
    ) {
        itemApiService.getAllItems(loadingHandler) { itemList ->
            scope.launch {
                itemDao.deleteAllItems()
                for (item in itemList) {
                    itemDao.insertItem(item)
                }
                loadingHandler.onSuccess()
            }
        }
    }

}