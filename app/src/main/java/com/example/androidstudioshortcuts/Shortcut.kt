package com.example.androidstudioshortcuts

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "shortcut")
@Parcelize
data class Shortcut(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var windows: String,
    var mac: String,
    var description: String
):Parcelable

// putting Shortcut into model package creates Directions related error, due to path change
