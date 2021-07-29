package com.niran.restaurantapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.niran.restaurantapplication.database.daos.ItemDao
import com.niran.restaurantapplication.database.daos.StarterDao
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.database.models.Starter
import com.niran.restaurantapplication.utils.AppUtils
import com.niran.restaurantapplication.utils.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Item::class, Starter::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun starterDao(): StarterDao

    class RoomCallBack(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database)
                }
            }
        }

        private suspend fun populateDatabase(database: AppDatabase) {
            populateItemTable(database.itemDao())
            populateStarterTable(database.starterDao())
        }

        private suspend fun populateStarterTable(starterDao: StarterDao){

            starterDao.deleteStarter()

            starterDao.insertStarter(Starter())

        }

        private suspend fun populateItemTable(itemDao: ItemDao) {
//            itemDao.deleteAllItems()

            var id = 1
            repeat(0) {
                itemDao.insertItem(AppUtils.createNewFood(id))
                id++
            }

        }

    }

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "restaurant_app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomCallBack(scope))
                    .build()

                INSTANCE = instance

                instance
            }

        }

    }

}