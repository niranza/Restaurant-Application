package com.niran.restaurantapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.niran.restaurantapplication.repositories.DataVersionRepository
import com.niran.restaurantapplication.repositories.ItemRepository
import com.niran.restaurantapplication.utils.LoadingHandler
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val itemRepository: ItemRepository,
    private val dataVersionRepository: DataVersionRepository
) : ViewModel() {

    fun loadSplashScreen(loadingHandler: LoadingHandler) = viewModelScope.launch {
        try {
            val databaseDataVersionObj = dataVersionRepository.getDatabaseDataVersionObj()
            Log.d("TAG", "data version from database: ${databaseDataVersionObj.dataVersion}")
            val netWorkDataVersion = dataVersionRepository.getNetworkDataVersion()

            if (databaseDataVersionObj.dataVersion != netWorkDataVersion) {
                Log.d("TAG", "start executing data update")
                itemRepository.insertAllItems()
                dataVersionRepository.insertDataVersion(
                    databaseDataVersionObj.copy(dataVersion = netWorkDataVersion)
                )
                Log.d("TAG", "finished executing data update")
            }

            loadingHandler.onSuccess()

        } catch (error: Exception) {
            loadingHandler.onFailure(error)
        }
    }

}

class SplashScreenViewModelFactory(
    private val itemRepository: ItemRepository,
    private val dataVersionRepository: DataVersionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashScreenViewModel(itemRepository, dataVersionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}