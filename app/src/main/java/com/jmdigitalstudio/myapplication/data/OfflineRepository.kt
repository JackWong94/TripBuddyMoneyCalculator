package com.jmdigitalstudio.myapplication.data

import kotlinx.coroutines.flow.Flow

class OfflineRepository(private val tripDao: TripDao, private val itemDao: ItemDao, private val personDao: PersonDao) : Repository {
    override fun getAllTripsStream(): Flow<List<Trip>> = tripDao.getAllTrips()
    override fun getTripStream(id: Int): Flow<Trip?> =tripDao.getTrip(id)
    override suspend fun insertTrip(trip: Trip) = tripDao.insert(trip)
    override suspend fun deleteTrip(trip: Trip) = tripDao.delete(trip)
    override suspend fun updateTrip(trip: Trip) = tripDao.update(trip)

    //Items related
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)
    override suspend fun insertItem(item: Item) = itemDao.insert(item)
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)
    override suspend fun updateItem(item: Item) = itemDao.update(item)

    //People related
    override fun getAllPeopleStream(): Flow<List<Person>> = personDao.getAllPeople()
    override fun getPersonStream(id: Int): Flow<Person?> = personDao.getPerson(id)
    override suspend fun insertPerson(person: Person) = personDao.insert(person)
    override suspend fun deletePerson(person: Person) = personDao.delete(person)
    override suspend fun updatePerson(person: Person) = personDao.update(person)
}