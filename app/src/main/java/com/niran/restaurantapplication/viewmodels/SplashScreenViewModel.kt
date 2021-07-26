package com.niran.restaurantapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    init {
        loadSplashScreen()
    }

    fun loadSplashScreen() {
        viewModelScope.launch {

            //load data

        }
    }

}