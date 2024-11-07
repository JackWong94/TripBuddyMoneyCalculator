package com.jmdigitalstudio.myapplication.data

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "trip")
data class Trip(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)

class TripConverter {
    @TypeConverter
    fun fromString(value: String): Trip {
        return Gson().fromJson(value, Trip::class.java)
    }

    @TypeConverter
    fun fromPerson(trip: Trip): String {
        return Gson().toJson(trip)
    }
}

class TripListConverter {
    @TypeConverter
    fun fromString(value: String): List<Trip> {
        val type = object : TypeToken<List<Trip>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<Trip>): String {
        return Gson().toJson(list)
    }
}