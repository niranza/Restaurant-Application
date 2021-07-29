package com.niran.restaurantapplication.viewmodels

import androidx.lifecycle.*
import com.niran.restaurantapplication.repositories.DataDateRepository
import com.niran.restaurantapplication.repositories.DataVersionRepository
import com.niran.restaurantapplication.repositories.ItemRepository
import com.niran.restaurantapplication.utils.LoadingHandler
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    private val itemRepository: ItemRepository,
    private val dataVersionRepository: DataVersionRepository,
    private val dataDateRepository: DataDateRepository
) : ViewModel() {

    private val _loadDataEvent = MutableLiveData<Boolean>()
    val loadDataEvent: LiveData<Boolean> = _loadDataEvent

    init {
        onLoadData()
    }

    fun loadSplashScreen(loadingHandler: LoadingHandler) = viewModelScope.launch {
        try {
            ///calculating time per update
            if (!dataDateRepository.isReadyForUpdate()) {
                loadingHandler.onSuccess()
                return@launch
            }

            val databaseDataVersionObj = dataVersionRepository.getDatabaseDataVersionObj()
            val netWorkDataVersion = dataVersionRepository.getNetworkDataVersion()

            //looking for change in version
            if (databaseDataVersionObj.dataVersion == netWorkDataVersion) {
                loadingHandler.onSuccess()
                return@launch
            }

            itemRepository.insertAllItems()

            dataVersionRepository.insertDataVersion(
                databaseDataVersionObj.copy(dataVersion = netWorkDataVersion)
            )

            loadingHandler.onSuccess()

        } catch (error: Exception) {
            loadingHandler.onFailure(error)
        }
    }


    //region Events

    fun onLoadData() {
        _loadDataEvent.value = true
    }

    fun onLoadDataFinished() {
        _loadDataEvent.value = false
    }

    //endregion
}

class MainViewModelFactory(
    private val itemRepository: ItemRepository,
    private val dataVersionRepository: DataVersionRepository,
    private val dataDateRepository: DataDateRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(itemRepository, dataVersionRepository, dataDateRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}