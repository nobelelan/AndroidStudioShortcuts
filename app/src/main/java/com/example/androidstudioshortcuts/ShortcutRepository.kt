package com.example.androidstudioshortcuts

import androidx.lifecycle.LiveData

class ShortcutRepository(private val shortcutDao: ShortcutDao) {

    val getAllData: LiveData<List<Shortcut>> = shortcutDao.getAllData()

    suspend fun insertData(shortcut: Shortcut){
        shortcutDao.insertData(shortcut)
    }

    suspend fun updateData(shortcut: Shortcut){
        shortcutDao.updateData(shortcut)
    }

    suspend fun deleteItem(shortcut: Shortcut){
        shortcutDao.deleteItem(shortcut)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Shortcut>>{
        return shortcutDao.searchDatabase(searchQuery)
    }

}