package com.niran.restaurantapplication.repositories

import com.niran.restaurantapplication.database.api.ItemApiService
import com.niran.restaurantapplication.database.daos.ItemDao
import com.niran.restaurantapplication.utils.FoodTypes
import com.niran.restaurantapplication.utils.LoadingHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ItemRepository(
    private val itemDao: ItemDao,
    private val itemApiService: ItemApiService
) {

    val foodList = itemDao.getItemsByType(FoodTypes.FOOD.ordinal)

    val beverageList = itemDao.getItemsByType(FoodTypes.FOOD.ordinal)

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