package com.example.androidstudioshortcuts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
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