package com.example.androidstudioshortcuts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShortcutDao {

    @Query("SELECT * FROM shortcut ORDER BY id DESC")
    fun getAllData(): LiveData<List<Shortcut>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(shortcut: Shortcut)
}