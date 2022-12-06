package com.example.androidstudioshortcuts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShortcutDao {

    @Query("SELECT * FROM shortcut ORDER BY id DESC")
    fun getAllData(): LiveData<List<Shortcut>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(shortcut: Shortcut)

    @Update
    suspend fun updateData(shortcut: Shortcut)
}