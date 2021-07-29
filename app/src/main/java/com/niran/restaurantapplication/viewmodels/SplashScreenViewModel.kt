package com.niran.restaurantapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.niran.restaurantapplication.repositories.ItemRepository
import com.niran.restaurantapplication.utils.LoadingHandler

class SplashScreenViewModel(
    private val itemRepository: ItemRepository,
) : ViewModel() {

    fun loadSplashScreen(loadingHandler: LoadingHandler) {
        itemRepository.insertAllItems(viewModelScope, loadingHandler)
    }

}

class SplashScreenViewModelFactory(
    private val itemRepository: ItemRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashScreenViewModel(itemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}