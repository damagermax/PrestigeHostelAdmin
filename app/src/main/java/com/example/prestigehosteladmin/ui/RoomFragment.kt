package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.adapter.NavigateToDetailScreen
import com.example.prestigehosteladmin.adapter.RoomAdapter
import com.example.prestigehosteladmin.databinding.FragmentRoomBinding
import com.example.prestigehosteladmin.model.RoomModel
import com.example.prestigehosteladmin.utils.toastMessage
import com.example.prestigehosteladmin.viewmodel.RoomViewModel


/**
 *
 * create an instance of this fragment.
 */
class RoomFragment : Fragment(R.layout.fragment_room), NavigateToDetailScreen {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private lateinit var roomViewModel: RoomViewModel
    private lateinit var roomAdapter: RoomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        binding.searchView.isIconifiedByDefault = true // make searchView active

        binding.floatingActionButton.setOnClickListener {
            val action = RoomFragmentDirections.actionRoomFragmentToAddRoomFragment()
            findNavController().navigate(action)
        }


    }

    private fun getRoomsFromDatabase() {
        roomViewModel.getRooms().observe(viewLifecycleOwner) { response ->

            response.rooms?.let { rooms ->
                setUpRecyclerView(rooms)

            }

            response.exception?.let { exception ->
                exception.message?.let { message ->
                    requireContext().toastMessage(message)

                }

            }

        }
    }

    private fun setUpRecyclerView(rooms: List<RoomModel>) {

        roomAdapter = RoomAdapter(this)
        binding.roomRecyclerView.setHasFixedSize(true)
        roomAdapter.setData(rooms)
        binding.roomRecyclerView.adapter = roomAdapter
    }

    override fun selectedRoom(roomModel: RoomModel) {

    }

}