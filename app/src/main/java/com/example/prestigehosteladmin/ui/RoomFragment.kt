package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.adapter.NavigateToDetailScreen
import com.example.prestigehosteladmin.adapter.RoomAdapter
import com.example.prestigehosteladmin.databinding.FragmentRoomBinding
import com.example.prestigehosteladmin.databinding.RoomCardBinding
import com.example.prestigehosteladmin.model.RoomModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 *
 * create an instance of this fragment.
 */
class RoomFragment : Fragment(R.layout.fragment_room), NavigateToDetailScreen {

    private var _binding:FragmentRoomBinding?=null
    private val binding get() = _binding!!

    private lateinit var roomAdapter: RoomAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        // Inflate the layout for this fragment
        _binding= FragmentRoomBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.searchView.isIconifiedByDefault=true // make searchView active
        
        binding.floatingActionButton.setOnClickListener {
            val action=RoomFragmentDirections.actionRoomFragmentToAddRoomFragment()
            findNavController().navigate(action)
        }


        // call functions here
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val dummy = arrayListOf<RoomModel>(
            RoomModel("2nd F 9","£ 2000",3,2,kitchen = true,false),
            RoomModel("3rd F 17","£ 1500",4,0,kitchen = true,true),
            RoomModel("4th F 26","£ 1700",1,1,kitchen = true,false),
            RoomModel("G F 20","£ 2100",4,2,kitchen = true,true),
            RoomModel("1st F 35","£ 4000",4,0,kitchen = true,false),
            RoomModel("9th F 1","£ 325",2,1,kitchen = true,true),
            RoomModel("6th F 4","£ 235",4,0,kitchen = false,true),
            RoomModel("2nd F 69","£ 385",4,3,kitchen = true,false),
            RoomModel("1st F 10","£ 385",3,3,kitchen = false,true),
            RoomModel("G F 28","£ 585",1,0,kitchen = true,true),
            RoomModel("2nd F 8","£ 6485",4,4,kitchen = true,false),
            RoomModel("7th F 28","£ 585",4,3,kitchen = false,false),
            RoomModel("10th F 18","£ 665",2,2,kitchen = false,false),
            RoomModel("5th F 258","£ 7785",4,0,kitchen = true,false),
        )
        roomAdapter= RoomAdapter(this)
        binding.roomRecyclerView.setHasFixedSize(true)
        roomAdapter.setData(dummy)
        binding.roomRecyclerView.adapter=roomAdapter
    }

    override fun selectedRoom(roomModel: RoomModel) {

    }

}