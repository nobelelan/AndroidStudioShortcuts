package com.example.androidstudioshortcuts

import androidx.lifecycle.LiveData

class ShortcutRepository(private val shortcutDao: ShortcutDao) {

    val getAllData: LiveData<List<Shortcut>> = shortcutDao.getAllData()

    suspend fun insertData(shortcut: Shortcut){
        shortcutDao.insertData(shortcut)
    }

}