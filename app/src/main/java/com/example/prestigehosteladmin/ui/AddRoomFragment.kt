package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.databinding.FragmentAddRoomBinding
import com.example.prestigehosteladmin.databinding.FragmentSignInBinding
import com.example.prestigehosteladmin.viewmodel.RoomViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 *
 * create an instance of this fragment.
 */
class AddRoomFragment : BottomSheetDialogFragment() {

    private lateinit var roomViewModel: RoomViewModel
    private var _binding: FragmentAddRoomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddRoomBinding.inflate(inflater, container, false)
        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}