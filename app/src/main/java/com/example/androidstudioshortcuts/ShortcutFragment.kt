package com.example.androidstudioshortcuts

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudioshortcuts.databinding.FragmentShortcutBinding
import com.google.android.material.snackbar.Snackbar


class ShortcutFragment : Fragment(), SearchView.OnQueryTextListener {

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
        
        // set menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shortcut_fragment_menu, menu)

        val search = menu.findItem(R.id.menuSearch)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searchThroughDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            searchThroughDatabase(query)
        }
        return true
    }

    private fun searchThroughDatabase(query: String){
        val searchQuery = "%$query%"
        mShortcutViewModel.searchDatabase(searchQuery).observe(this, Observer { list ->
            list?.let {
                adapter.setData(it.asReversed())
            }
        })
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