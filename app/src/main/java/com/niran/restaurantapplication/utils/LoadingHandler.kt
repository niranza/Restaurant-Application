package com.niran.restaurantapplication.utils

interface LoadingHandler {

    fun onSuccess()

    fun onFailure(error: Exception)

}