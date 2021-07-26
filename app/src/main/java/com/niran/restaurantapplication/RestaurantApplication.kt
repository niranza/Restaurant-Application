package com.niran.restaurantapplication

import androidx.multidex.MultiDexApplication
import com.niran.restaurantapplication.database.AppDatabase
import com.niran.restaurantapplication.database.FirebaseFireStore
import com.niran.restaurantapplication.repositories.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RestaurantApplication : MultiDexApplication() {

    private val scope = CoroutineScope(SupervisorJob())

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this, scope) }

    val itemRepository: ItemRepository by lazy {
        ItemRepository(database.itemDao(), FirebaseFireStore.itemApiService)
    }

}