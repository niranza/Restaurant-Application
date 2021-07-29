package com.niran.restaurantapplication.utils

import com.niran.restaurantapplication.R

enum class FoodTypes(val titleId: Int) {
    FOOD(R.string.food),
    BEVERAGE(R.string.beverage)
}

const val ITEM_COLLECTION = "Item"
const val DATA_VERSION_COLLECTION = "DataVersion"
const val DATA_VERSION_DOCUMENT = "dataVersion"

const val MAX_NUMBER_OF_ITEMS_PER_MEAL = 10

const val MENU_NOTIFICATION_ID = 0

const val ITEM_ID = "item_id"
const val ITEM_NAME = "item_type"
const val ITEM_TYPE = "item_type"
const val ITEM_PRICE = "item_price"
const val ITEM_IMAGE_ID = "item_image_id"
const val INGREDIENT_NAME = "ingredient_name"
const val IS_INGREDIENT_REMOVABLE = "is_ingredient_removable"
const val IS_INGREDIENT_REMOVED = "is_ingredient_removed"