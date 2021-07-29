package com.niran.restaurantapplication.repositories

import android.util.Log
import com.niran.restaurantapplication.database.daos.DataDateDao
import com.niran.restaurantapplication.database.models.DataDate
import com.niran.restaurantapplication.utils.DAYS_IN_MILLI
import java.util.*

class DataDateRepository(private val dataDateDao: DataDateDao) {

    suspend fun isReadyForUpdate(): Boolean {
        val dataDateObj = getDataDateObj()
        val currentDate = currentDateInMillis()
        val isReadyForUpdate = currentDate - dataDateObj.dataDate >= TIME_BETWEEN_UPDATES_IN_MILLIS

        if (isReadyForUpdate) insertDataDate(dataDateObj.copy(dataDate = currentDate))

        Log.i("DataDateRepository", "time remaining: ${currentDate - dataDateObj.dataDate}")
        Log.i("DataDateRepository", "required time: $TIME_BETWEEN_UPDATES_IN_MILLIS")

        return isReadyForUpdate
    }

    private suspend fun insertDataDate(dataDateObj: DataDate) =
        dataDateDao.insertDataDate(dataDateObj)

    private suspend fun getDataDateObj(): DataDate =
        dataDateDao.getDataDate()
            ?: DataDate(dataDate = currentDateInMillis() - TIME_BETWEEN_UPDATES_IN_MILLIS)

    private fun currentDateInMillis(): Long = Calendar.getInstance().time.time

    companion object {
        private const val TIME_BETWEEN_UPDATES_IN_MILLIS = DAYS_IN_MILLI
    }
}