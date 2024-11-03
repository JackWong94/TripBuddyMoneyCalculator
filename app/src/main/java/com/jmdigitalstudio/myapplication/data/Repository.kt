package com.jmdigitalstudio.myapplication.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface Repository {

    //Items related
    fun getAllItemsStream(): Flow<List<Item>>
    fun getItemStream(id: Int): Flow<Item?>
    suspend fun insertItem(item: Item)
    suspend fun deleteItem(item: Item)
    suspend fun updateItem(item: Item)

    //People related
    fun getAllPeopleStream(): Flow<List<Person>>
    fun getPersonStream(id: Int): Flow<Person?>
    suspend fun insertPerson(person: Person)
    suspend fun deletePerson(person: Person)
    suspend fun updatePerson(person: Person)
}