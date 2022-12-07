package com.example.androidstudioshortcuts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidstudioshortcuts.Shortcut

@Database(entities = [Shortcut::class], version = 2, exportSchema = false)
abstract class ShortcutDatabase: RoomDatabase() {

    abstract fun shortcutDao(): ShortcutDao

    companion object {
        // Volatile -> Writes to this field are immediately made visible to other threads
        @Volatile
        private var INSTANCE: ShortcutDatabase? = null

        fun getDatabase(context: Context): ShortcutDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShortcutDatabase::class.java,
                    "shortcut_db"
                )
                    .createFromAsset("shortcut_database.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }

}