package com.niran.restaurantapplication.utils

import androidx.room.TypeConverter
import com.niran.restaurantapplication.database.models.Ingredient
import com.niran.restaurantapplication.database.models.Ingredients

class Converters {

    @TypeConverter
    fun fromIngredients(ingredients: Ingredients): String {
        val stringList = mutableListOf<String>()

        if (ingredients.ingredientList.isEmpty()) return ""

        for (ingredient in ingredients.ingredientList) {
            val ingredientStringList = listOf(
                ingredient.ingredientName,
                ingredient.isIngredientRemovable.toString(),
                ingredient.isIngredientRemoved.toString()
            )
            stringList.add(ingredientStringList.joinToString("-"))
        }
        return stringList.joinToString("\n")
    }

    @TypeConverter
    fun toIngredients(string: String): Ingredients {
        val ingredientList = mutableListOf<Ingredient>()
        val ingredientStringList = string.split("\n")

        if (ingredientStringList[0].isBlank()) return Ingredients()

        for (ingredientString in ingredientStringList) {
            ingredientString.split("-").also {
                ingredientList.add(
                    Ingredient(
                        ingredientName = it[0],
                        isIngredientRemovable = it[1].toBoolean(),
                        isIngredientRemoved = it[2].toBoolean()
                    )
                )
            }
        }
        return Ingredients(ingredientList)
    }
}