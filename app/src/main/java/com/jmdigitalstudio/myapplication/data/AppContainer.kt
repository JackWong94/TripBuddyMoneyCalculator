package com.jmdigitalstudio.myapplication.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
    val peopleRepository: PeopleRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
    /**
     * Implementation for [PeopleRepository]
     */
    override val peopleRepository: PeopleRepository by lazy {
        OfflinePeopleRepository(InventoryDatabase.getDatabase(context).personDao())
    }
}