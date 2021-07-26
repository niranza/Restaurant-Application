package com.niran.restaurantapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.niran.restaurantapplication.repositories.ItemRepository

class FoodViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    val foodList = itemRepository.foodList.asLiveData()

}

class FoodViewModelFactory(private val itemRepository: ItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(itemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}