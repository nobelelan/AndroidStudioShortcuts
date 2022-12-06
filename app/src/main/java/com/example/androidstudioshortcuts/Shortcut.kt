package com.example.androidstudioshortcuts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shortcut")
data class Shortcut(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var windows: String,
    var mac: String,
    var description: String
)
