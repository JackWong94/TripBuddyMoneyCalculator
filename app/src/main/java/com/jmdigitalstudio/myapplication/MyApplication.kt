package com.jmdigitalstudio.myapplication

import android.app.Application
import com.jmdigitalstudio.myapplication.data.AppContainer
import com.jmdigitalstudio.myapplication.data.AppDataContainer

class MyApplication : Application() {
    lateinit var appDataContainer: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        // Initialize the AppContainer with the application context
        appDataContainer = AppDataContainer(this)
    }
}