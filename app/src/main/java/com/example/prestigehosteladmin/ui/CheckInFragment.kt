package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.adapter.CheckInAdapter
import com.example.prestigehosteladmin.adapter.NavigateToDetailScreen
import com.example.prestigehosteladmin.adapter.NavigateToViewDetailsScreen
import com.example.prestigehosteladmin.model.CheckInModel
import com.example.prestigehosteladmin.model.RoomModel


/**
 *
 * create an instance of this fragment.
 */
class CheckInFragment : Fragment() ,NavigateToViewDetailsScreen{
    private lateinit var checkInAdapter: CheckInAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_ckeck_in, container, false)

        // hook views
        recyclerView = view.findViewById(R.id.roomRecyclerView)

        setUpRecyclerView()
        return view
    }

    private fun setUpRecyclerView() {
        val dummy = arrayListOf<CheckInModel>(
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),
            CheckInModel("gljkngw","05-38-2000","Maxwell Takyi","$ 990","1st F 4333"),

        )
        checkInAdapter= CheckInAdapter(this)
        recyclerView.setHasFixedSize(true)
        checkInAdapter.setData(dummy)
        recyclerView.adapter=checkInAdapter
    }

    override fun selectedCheckInItem(checkInModel: CheckInModel) {
        TODO("Not yet implemented")
    }


}