package com.niran.restaurantapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.niran.restaurantapplication.repositories.ItemRepository

class BeverageViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    val beverageList = itemRepository.beverageList.asLiveData()

}

class BeverageViewModelFactory(private val itemRepository: ItemRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeverageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeverageViewModel(itemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}