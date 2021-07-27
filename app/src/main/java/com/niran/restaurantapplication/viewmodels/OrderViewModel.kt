package com.niran.restaurantapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.niran.restaurantapplication.repositories.ItemRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    val itemList = itemRepository.orderedItemsList.asLiveData()

    fun deleteAllOrderedItems() = viewModelScope.launch { itemRepository.deleteOrderedItems() }

}

class OrderViewModelFactory(private val itemRepository: ItemRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrderViewModel(itemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}