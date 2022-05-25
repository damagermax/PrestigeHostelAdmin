package com.example.prestigehosteladmin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.adapter.BannerAdapter
import com.example.prestigehosteladmin.databinding.FragmentDashboardBinding
import com.example.prestigehosteladmin.model.BannerModel
import kotlin.math.E


/**
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var bannerAdapter: BannerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addBanner.setOnClickListener {
            addBannerFromGallery()
        }
        // call function here
        setUpRecyclerView()
    }

    private fun addBannerFromGallery() {

        Toast.makeText(requireContext(), "das", Toast.LENGTH_LONG).show()
        //@todo pick image from gallery
    }


    private fun setUpRecyclerView() {
        val dummy = arrayListOf<BannerModel>(
            BannerModel(
                "djk",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQb1YGTfhEQx274XRsWys5tNlQWymG6Q64sDA&usqp=CAU"
            ),
            BannerModel(
                "db",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsuoS36jE0znlVBfzk2XZ2d-jBQ7O5MMnURw&usqp=CAU"
            ),
            BannerModel(
                "db",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3UKkXrcA5j7PzJJRnFTdk5IicSaGGK6U9vA&usqp=CAU"
            ),
            BannerModel(
                "db",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrVJl5C_IBV3vReFiZLwxwifi4i683cJhUSw&usqp=CAU"
            ),
        )



        bannerAdapter = BannerAdapter()
        bannerAdapter.setData(dummy)
        binding.bannerRecyclerView.setHasFixedSize(true)
        binding.bannerRecyclerView.adapter = bannerAdapter


    }
}