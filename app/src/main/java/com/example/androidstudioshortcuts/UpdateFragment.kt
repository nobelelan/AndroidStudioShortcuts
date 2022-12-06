package com.example.androidstudioshortcuts

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidstudioshortcuts.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val mShortcutViewModel: ShortcutViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentUpdateBinding.inflate(layoutInflater, container, false)

        binding.editTextDescription.setText(args.currentItem.description)
        binding.editTextWindows.setText(args.currentItem.windows)
        binding.editTextMac.setText(args.currentItem.mac)

        // set menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuUpdate){
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val mDescription = binding.editTextDescription.text.toString()
        val mWindows = binding.editTextWindows.text.toString()
        val mMac = binding.editTextMac.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mDescription, mWindows, mMac)
        if (validation){
            val updatedData = Shortcut(args.currentItem.id, mWindows, mMac, mDescription)
            mShortcutViewModel.updateData(updatedData)
            Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_shortcutFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out required fields!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}