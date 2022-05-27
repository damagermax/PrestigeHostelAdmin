package com.example.prestigehosteladmin.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prestigehosteladmin.adapter.BannerAdapter
import com.example.prestigehosteladmin.databinding.FragmentDashboardBinding
import com.example.prestigehosteladmin.model.BannerModel


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

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 3)

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