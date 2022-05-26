package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.databinding.FragmentAddRoomBinding
import com.example.prestigehosteladmin.databinding.FragmentSignInBinding
import com.example.prestigehosteladmin.utils.toastMessage
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

        binding.saveRoomBtn.setOnClickListener {
            validateFields()
        }

    }

    private fun validateFields() {
        val number = binding.roomNumberInput.text.toString()
        val price = binding.roomPriceInput.text.toString()
        val type = binding.roomTypeSpinner.selectedItem.toString()
        val gender = binding.roomGenderSpinner.selectedItem.toString()
        val kitchen = binding.roomKitchenSwitch.isChecked
        val bathroom = binding.roomBathroomSwitch.isChecked
        var numberOfGuests = 0


        when {
            number.isEmpty() -> {
                requireContext().toastMessage("Enter room number")
                return

            }
            price.isEmpty() -> {
                requireContext().toastMessage("Enter room price")
                return
            }
            gender == "Select Gender" -> {
                requireContext().toastMessage("Select an option")
                return
            }
            type == "Select room type" -> {
                requireContext().toastMessage("Select an option")
                return
            }
            else -> {
                numberOfGuests = when (type) {
                    "1 in a room" -> {
                        1
                    }
                    "2 in a room" -> {
                        2
                    }
                    "3 in a room" -> {
                        3
                    }
                    else -> {
                        4
                    }
                }

                val newRoomMap = HashMap<String, Any>()
                newRoomMap["number"] = number
                newRoomMap["price"] = price
                newRoomMap["gender"] = gender
                newRoomMap["type"] = type
                newRoomMap["bathroom"] = bathroom
                newRoomMap["kitchen"] = kitchen
                newRoomMap["numberOfGuests"] = numberOfGuests
                newRoomMap["bedsLeft"] = numberOfGuests

                saveRoomToDatabase(newRoomMap)
            }
        }

    }

    private fun saveRoomToDatabase(newRoomMap: HashMap<String, Any>) {

        roomViewModel.addRoom(newRoomMap).observe(viewLifecycleOwner) { response ->

            response.message?.let { msg ->
                requireContext().toastMessage(msg)
            }
            response.exception?.let { exception ->
                val msg = exception.localizedMessage
                if (msg != null) {
                    requireContext().toastMessage(msg)
                }
            }

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}