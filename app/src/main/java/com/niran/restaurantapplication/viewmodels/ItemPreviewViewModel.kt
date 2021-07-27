package com.niran.restaurantapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.repositories.ItemRepository
import kotlinx.coroutines.launch

class ItemPreviewViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    fun getItem(itemId: Int) = itemRepository.getItem(itemId)

    fun updateItem(item: Item) = viewModelScope.launch { itemRepository.updateItem(item) }

    fun deleteItem(item: Item) = viewModelScope.launch { itemRepository.deleteItem(item) }

    fun insertItem(item: Item) = viewModelScope.launch { itemRepository.insertItem(item) }
}

class ItemPreviewViewModelFactory(private val itemRepository: ItemRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemPreviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemPreviewViewModel(itemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}