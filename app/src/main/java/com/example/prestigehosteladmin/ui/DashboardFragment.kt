package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.adapter.BannerAdapter
import com.example.prestigehosteladmin.model.BannerModel
import kotlin.math.E


/**
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        // hook view
        recyclerView = view.findViewById(R.id.bannerRecyclerView)

        // call function here
        setUpRecyclerView()
        return view
    }

    private fun setUpRecyclerView() {
        val dummy= arrayListOf<BannerModel>(
            BannerModel("djk","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQb1YGTfhEQx274XRsWys5tNlQWymG6Q64sDA&usqp=CAU"),
            BannerModel("db","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsuoS36jE0znlVBfzk2XZ2d-jBQ7O5MMnURw&usqp=CAU"),
            BannerModel("db","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3UKkXrcA5j7PzJJRnFTdk5IicSaGGK6U9vA&usqp=CAU"),
            BannerModel("db","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrVJl5C_IBV3vReFiZLwxwifi4i683cJhUSw&usqp=CAU"),
        )



        bannerAdapter= BannerAdapter()
        bannerAdapter.setData(dummy)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=bannerAdapter


    }
}