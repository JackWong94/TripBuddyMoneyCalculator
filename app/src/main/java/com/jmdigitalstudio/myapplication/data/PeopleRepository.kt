package com.jmdigitalstudio.myapplication.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Person] from a given data source.
 */
interface PeopleRepository {
    /**
     * Retrieve all the people from the the given data source.
     */
    fun getAllPeopleStream(): Flow<List<Person>>

    /**
     * Retrieve an person from the given data source that matches with the [id].
     */
    fun getPersonStream(id: Int): Flow<Person?>

    /**
     * Insert person in the data source
     */
    suspend fun insertPerson(person: Person)

    /**
     * Delete person from the data source
     */
    suspend fun deletePerson(person: Person)

    /**
     * Update person in the data source
     */
    suspend fun updatePerson(person: Person)
}