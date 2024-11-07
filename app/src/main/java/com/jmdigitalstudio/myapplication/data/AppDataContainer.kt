package com.jmdigitalstudio.myapplication.data

import android.content.Context

class AppDataContainer(private val context: Context) : AppContainer {

    // Initialize the Database instance
    private val database: InventoryDatabase by lazy {
        InventoryDatabase.getDatabase(context)
    }

    // Provide a single instance of Repository, initialized lazily
    override val repository: Repository by lazy {
        OfflineRepository(
            database.tripDao(),
            database.itemDao(),
            database.personDao()
        )
    }
}