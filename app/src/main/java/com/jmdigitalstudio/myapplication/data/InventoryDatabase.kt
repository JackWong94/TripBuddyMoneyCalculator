package com.jmdigitalstudio.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jmdigitalstudio.myapplication.DoubleListConverter

@Database(entities = [Trip::class, Item::class, Person::class], version = 1, exportSchema = false)
@TypeConverters(
    DoubleListConverter::class,
    TripConverter::class,
    TripListConverter::class,
    ItemConverter::class,
    ItemListConverter::class,
    PersonConverter::class,
    PersonListConverter::class
)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun tripDao(): TripDao
    abstract fun itemDao(): ItemDao
    abstract fun personDao(): PersonDao
    companion object {
        @Volatile
        private var instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
        }
    }
}
