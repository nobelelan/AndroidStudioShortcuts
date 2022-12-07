package com.example.androidstudioshortcuts.fragments.shortcut.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudioshortcuts.R
import com.example.androidstudioshortcuts.Shortcut
import com.example.androidstudioshortcuts.databinding.ShortcutRowLayoutBinding
import com.example.androidstudioshortcuts.fragments.shortcut.ShortcutFragmentDirections

class ShortcutAdapter: RecyclerView.Adapter<ShortcutAdapter.ShortcutViewHolder>() {

    var dataList = emptyList<Shortcut>()

    inner class ShortcutViewHolder(val binding: ShortcutRowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortcutViewHolder {
        return ShortcutViewHolder(ShortcutRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShortcutViewHolder, position: Int) {
        holder.binding.textViewDescription.text = dataList[position].description
        holder.binding.textViewWindows.text = dataList[position].windows
        holder.binding.textViewMac.text = dataList[position].mac

        holder.binding.shortcutRowBackground.setOnClickListener {
            val action = ShortcutFragmentDirections.actionShortcutFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }

        holder.binding.shortcutRowBackground.animation = (
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recyclerview_item_anim)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(shortcut: List<Shortcut>){
        val shortcutDiffUtil = ShortcutDiffUtil(dataList, shortcut)
        val shortcutDiffResult = DiffUtil.calculateDiff(shortcutDiffUtil)
        this.dataList = shortcut
        shortcutDiffResult.dispatchUpdatesTo(this)
    }
}