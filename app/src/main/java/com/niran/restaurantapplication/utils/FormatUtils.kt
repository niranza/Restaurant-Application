package com.niran.restaurantapplication.utils

import com.niran.restaurantapplication.database.models.Ingredient

object FormatUtils {

    fun formatIngredients(ingredients: List<Ingredient>): String {
        val stringList = mutableListOf<String>()
        for (ingredient in ingredients)
            if (!ingredient.isIngredientRemoved)
                stringList.add(ingredient.ingredientName)
        return stringList.joinToString(", ")
    }

}