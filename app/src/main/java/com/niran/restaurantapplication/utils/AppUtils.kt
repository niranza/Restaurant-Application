package com.niran.restaurantapplication.utils

import com.niran.restaurantapplication.R
import com.niran.restaurantapplication.database.models.Ingredient
import com.niran.restaurantapplication.database.models.Ingredients
import com.niran.restaurantapplication.database.models.Item

object AppUtils {

    fun createNewFood(itemId: Int): Item = Item(
        itemId = itemId,
        itemName = "Pasta",
        itemPrice = 45.0,
        itemImageId = R.drawable.ic_food,
        itemType = FoodTypes.FOOD.ordinal,
        itemIngredients = Ingredients(
            listOf(
                Ingredient(
                    ingredientName = "Flour",
                ),
                Ingredient(
                    ingredientName = "Eggs",
                ),
                Ingredient(
                    ingredientName = "Tomatoes",
                ),
                Ingredient(
                    ingredientName = "Basil",
                ),
                Ingredient(
                    ingredientName = "Mushrooms",
                ),
                Ingredient(
                    ingredientName = "Salt & Pepper",
                ),
            )
        )
    )

    fun createNewBeverage(itemId: Int): Item = Item(
        itemId = itemId,
        itemName = "Coca Cola",
        itemPrice = 10.0,
        itemImageId = R.drawable.ic_food,
        itemType = FoodTypes.BEVERAGE.ordinal,
        itemIngredients = Ingredients(listOf())
    )

    fun formatIngredients(ingredients: List<Ingredient>): String {
        val stringList = mutableListOf<String>()
        for (ingredient in ingredients)
            if (!ingredient.isIngredientRemoved)
                stringList.add(ingredient.ingredientName)
        return stringList.joinToString(", ")
    }

}