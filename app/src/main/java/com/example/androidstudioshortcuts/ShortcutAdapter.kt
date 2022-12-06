package com.example.androidstudioshortcuts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudioshortcuts.databinding.ShortcutRowLayoutBinding

class ShortcutAdapter: RecyclerView.Adapter<ShortcutAdapter.ShortcutViewHolder>() {

    var dataList = emptyList<Shortcut>()

    inner class ShortcutViewHolder(val binding: ShortcutRowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortcutViewHolder {
        return ShortcutViewHolder(ShortcutRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShortcutViewHolder, position: Int) {
        holder.binding.textViewDescription.text = dataList[position].description
        holder.binding.textViewWindows.text = "Windows: ${dataList[position].windows}"
        holder.binding.textViewMac.text = "Mac: ${dataList[position].mac}"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(shortcut: List<Shortcut>){
        this.dataList = shortcut
        notifyDataSetChanged()
    }
}