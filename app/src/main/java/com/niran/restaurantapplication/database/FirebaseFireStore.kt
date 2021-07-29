package com.niran.restaurantapplication.database

import com.niran.restaurantapplication.database.api.DataVersionApiService
import com.niran.restaurantapplication.database.api.ItemApiService

object FirebaseFireStore {

    val itemApiService = ItemApiService()

    val dataVersionApiService = DataVersionApiService()

}