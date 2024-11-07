package com.jmdigitalstudio.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trip: Trip)
    @Update
    suspend fun update(trip: Trip)
    @Delete
    suspend fun delete(trip: Trip)
    @Query("SELECT * from trip WHERE id = :id")
    fun getTrip(id: Int): Flow<Trip>
    @Query("SELECT * from people ORDER BY name ASC")
    fun getAllTrips(): Flow<List<Trip>>
}