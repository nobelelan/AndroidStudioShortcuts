package com.example.androidstudioshortcuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudioshortcuts.databinding.FragmentShortcutBinding
import com.google.android.material.snackbar.Snackbar


class ShortcutFragment : Fragment() {

    private var _binding: FragmentShortcutBinding? = null
    private val binding get() = _binding!!

    private val mShortcutViewModel: ShortcutViewModel by viewModels()
    private val adapter: ShortcutAdapter by lazy { ShortcutAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentShortcutBinding.inflate(inflater, container, false)

        // setting up recyclerview
        setUpRecyclerView()

        mShortcutViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_shortcutFragment_to_addFragment)
        }

        return binding.root
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.shortcutRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        // swap to delete item
        swipeToDelete(recyclerView)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                mShortcutViewModel.deleteItem(deletedItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedData(viewHolder.itemView, deletedItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(itemView: View, deletedItem: Shortcut) {
        val snackBar = Snackbar.make(
            itemView,
            "Item deleted successfully.",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo"){
            mShortcutViewModel.insertData(deletedItem)
        }
        snackBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}