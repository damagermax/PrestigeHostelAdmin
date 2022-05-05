package com.example.prestigehosteladmin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.model.BannerModel
import com.example.prestigehosteladmin.model.CheckInModel
import com.example.prestigehosteladmin.model.RoomModel

class CheckInAdapter (private val navigateToViewDetailsScreen: NavigateToViewDetailsScreen):RecyclerView.Adapter<CheckInAdapter.CheckInViewHolder>(){

    private var mCheckInModel = emptyList<CheckInModel>()

    inner  class CheckInViewHolder(view: View):RecyclerView.ViewHolder(view){

        val priceTv: TextView = view.findViewById(R.id.orderRoomPrice)
        val numberTv: TextView = view.findViewById(R.id.orderRoomNumber)
        val guestNameTv: TextView = view.findViewById(R.id.orderGuestName)
        val dateTv: TextView = view.findViewById(R.id.orderedOn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CheckInViewHolder {
        return CheckInViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CheckInViewHolder, position: Int) {
        val currentCheckIn=mCheckInModel[position]
    }

    override fun getItemCount(): Int {
       return mCheckInModel.size
    }

    fun setData(mData: List<CheckInModel>){
        mCheckInModel=mData
    }
}
interface NavigateToViewDetailsScreen{
    fun selectedCheckInItem(checkInModel: CheckInModel)
}