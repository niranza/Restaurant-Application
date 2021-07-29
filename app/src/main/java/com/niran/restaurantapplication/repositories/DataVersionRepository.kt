package com.niran.restaurantapplication.repositories

import com.niran.restaurantapplication.database.api.DataVersionApiService
import com.niran.restaurantapplication.database.daos.DataVersionDao
import com.niran.restaurantapplication.database.models.DataVersion

class DataVersionRepository(
    private val dataVersionDao: DataVersionDao,
    private val dataVersionApiService: DataVersionApiService
) {

    suspend fun insertDataVersion(dataVersionObj: DataVersion) =
        dataVersionDao.insertDataVersion(dataVersionObj)

    suspend fun getDatabaseDataVersionObj(): DataVersion =
        dataVersionDao.getDataVersion() ?: DataVersion()

    suspend fun getNetworkDataVersion(): Int = dataVersionApiService.getDataVersion()

}