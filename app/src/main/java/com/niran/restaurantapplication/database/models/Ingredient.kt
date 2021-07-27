package com.niran.restaurantapplication.database.models

data class Ingredient(

    val ingredientName: String = "",

    val isIngredientRemovable: Boolean = false,

    val isIngredientRemoved: Boolean = false,

    )