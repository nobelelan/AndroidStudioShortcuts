package com.example.androidstudioshortcuts

import androidx.recyclerview.widget.DiffUtil

class ShortcutDiffUtil(
    private val oldList: List<Shortcut>,
    private val newList: List<Shortcut>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].windows == newList[newItemPosition].windows
                && oldList[oldItemPosition].mac == newList[newItemPosition].mac
    }
}