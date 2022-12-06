package com.example.androidstudioshortcuts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidstudioshortcuts.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mShortcutViewModel: ShortcutViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentAddBinding.inflate(inflater, container, false)


        // set menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAdd){
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mDescription = binding.editTextDescription.text.toString()
        val mWindows = binding.editTextWindows.text.toString()
        val mMac = binding.editTextMac.text.toString()
        val validation = mSharedViewModel.verifyDataFromUser(mDescription, mWindows, mMac)
        if (validation){
            val newData = Shortcut(0, mWindows, mMac, mDescription)
            mShortcutViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully inserted!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_shortcutFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out required fields!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}