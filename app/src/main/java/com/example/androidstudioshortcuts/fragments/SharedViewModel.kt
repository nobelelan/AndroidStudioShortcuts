package com.example.androidstudioshortcuts.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SharedViewModel(application: Application): AndroidViewModel(application) {

    fun verifyDataFromUser(description: String, windows: String, mac: String): Boolean{
        return (windows.isNotEmpty() && description.isNotEmpty() || description.isNotEmpty() && mac.isNotEmpty())
    }
}