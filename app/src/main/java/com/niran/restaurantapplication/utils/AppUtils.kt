package com.niran.restaurantapplication.utils

import com.niran.restaurantapplication.R
import com.niran.restaurantapplication.database.models.Ingredient
import com.niran.restaurantapplication.database.models.Ingredients
import com.niran.restaurantapplication.database.models.Item

object AppUtils {

    fun createNewItem(itemId: Int): Item = Item(
        itemId = itemId,
        itemName = "Bacon",
        itemPrice = 30.6,
        itemImageId = R.drawable.ic_food,
        itemType = FoodTypes.FOOD.ordinal,
        itemIngredients = Ingredients(
            listOf(
                Ingredient(
                    ingredientName = "meat",
                ),
                Ingredient(
                    ingredientName = "sugar",
                ),
                Ingredient(
                    ingredientName = "bread",
                ),
            )
        )
    )

}