package com.niran.restaurantapplication.database.api

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.utils.ITEM_COLLECTION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ItemApiService {

    private val itemCollectionRef = Firebase.firestore.collection(ITEM_COLLECTION)

    suspend fun saveItem(
        item: Item,
        context: Context
    ) {
        try {
            withContext(Dispatchers.IO) { itemCollectionRef.add(item).await() }
            Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun getAllItems(): List<Item> = withContext(Dispatchers.IO) {
        val result = itemCollectionRef.get().await()
        val itemList = mutableListOf<Item>()
        for (doc in result.documents) {
            doc.toObject(Item::class.java)?.let { item ->
                itemList.add(item)
            }
        }
        itemList
    }

}