package com.example.prestigehosteladmin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.model.BannerModel
import com.example.prestigehosteladmin.model.RoomModel
import com.squareup.picasso.Picasso


class BannerAdapter() : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private var mBannerModel = emptyList<BannerModel>()


    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageIv: ImageView = view.findViewById(R.id.bannerIv)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.banner_card, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val currentBannerModel = mBannerModel[position]

        Picasso.get().load(currentBannerModel.image).fit().into(holder.imageIv)


    }

    override fun getItemCount() = mBannerModel.size


    fun setData(bannerData: List<BannerModel>) {
        mBannerModel = bannerData
    }

}

