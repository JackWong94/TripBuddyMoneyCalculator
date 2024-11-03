package com.jmdigitalstudio.myapplication.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val repository: Repository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [Repository]
     */
    override val repository: Repository by lazy {
        OfflineRepository(InventoryDatabase.getDatabase(context).itemDao(), InventoryDatabase.getDatabase(context).personDao())
    }
}