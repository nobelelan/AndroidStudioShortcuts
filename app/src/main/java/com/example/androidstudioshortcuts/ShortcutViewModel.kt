package com.example.androidstudioshortcuts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShortcutViewModel(application: Application): AndroidViewModel(application) {

    private val shortcutDao = ShortcutDatabase.getDatabase(application).shortcutDao()
    private val repository: ShortcutRepository

    val getAllData: LiveData<List<Shortcut>>

    init {
        repository = ShortcutRepository(shortcutDao)
        getAllData = repository.getAllData
    }

    fun insertData(shortcut: Shortcut){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(shortcut)
        }
    }

    fun updateData(shortcut: Shortcut){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(shortcut)
        }
    }

    fun deleteItem(shortcut: Shortcut){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(shortcut)
        }
    }
}