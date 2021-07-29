package com.niran.restaurantapplication.utils

import com.niran.restaurantapplication.database.models.Item

object FormatUtils {

    fun formatIngredients(item: Item): String {
        val stringList = mutableListOf<String>()
        return if (item.isItemOrdered) {
            for (ingredient in item.itemIngredients.ingredientList.filter { !it.isIngredientRemoved })
                stringList.add(ingredient.ingredientName)
            stringList.joinToString(", ")
        } else {
            for (ingredient in item.itemIngredients.ingredientList)
                stringList.add(ingredient.ingredientName)
            stringList.joinToString(", ")
        }
    }

}