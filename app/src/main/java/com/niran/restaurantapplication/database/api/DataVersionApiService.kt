package com.niran.restaurantapplication.database.api

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.niran.restaurantapplication.database.models.DataVersion
import com.niran.restaurantapplication.utils.DATA_VERSION_COLLECTION
import com.niran.restaurantapplication.utils.DATA_VERSION_DOCUMENT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DataVersionApiService {

    private val dataVersionDocumentRef = Firebase.firestore.collection(DATA_VERSION_COLLECTION)
        .document(DATA_VERSION_DOCUMENT)

    suspend fun getDataVersion(): Int = withContext(Dispatchers.IO) {
        val result = dataVersionDocumentRef.get().await()
        val dataVersion = result.toObject(DataVersion::class.java)?.dataVersion
        Log.d("TAG", "data version from net work: $dataVersion")
        dataVersion!!
    }

}