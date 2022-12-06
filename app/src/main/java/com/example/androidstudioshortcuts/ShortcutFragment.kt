package com.example.androidstudioshortcuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudioshortcuts.databinding.FragmentShortcutBinding


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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}