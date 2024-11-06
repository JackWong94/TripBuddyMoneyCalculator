package com.jmdigitalstudio.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jmdigitalstudio.myapplication.MyApplication
import com.jmdigitalstudio.myapplication.data.Item
import com.jmdigitalstudio.myapplication.data.Person
import com.jmdigitalstudio.myapplication.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(private val repository: Repository) : ViewModel() {
    // LiveData or StateFlow to hold the list of items
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    // Function to load items from the repository
    fun loadItems() {
        viewModelScope.launch {
            // Fetch items from the repository (this could be a flow or a suspend function)
            repository.getAllItemsStream().collect { itemList ->
                _items.value = itemList // Set the collected item list to the MutableStateFlow
            }
        }
    }

    // Function to add an item
    fun addItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item) // Call the repository method to insert an item
            loadItems() // Reload items after insertion
        }
    }
    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                AppViewModel(application.appDataContainer.repository)
            }
        }
    }
}
