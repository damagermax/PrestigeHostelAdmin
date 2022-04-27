package com.example.prestigehosteladmin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prestigehosteladmin.R
import com.example.prestigehosteladmin.model.RoomModel


const val AVAILABLE: String = "Available"
const val ALMOST_FULL: String = "Almost Full"
const val FULL: String = "Full"
class RoomAdapter (private val navigateToDetailScreen: NavigateToDetailScreen):RecyclerView.Adapter<RoomAdapter.RoomViewHolder>(){
    private var mRoomModel = emptyList<RoomModel>()


    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val priceTv: TextView = view.findViewById(R.id.roomPrice)
        val numberTv: TextView = view.findViewById(R.id.roomNumber)
        val statusTv: TextView = view.findViewById(R.id.roomStatus)
        val statusIndicator: View = view.findViewById(R.id.roomStatusIndicator)
        val kitchenTv: TextView = view.findViewById(R.id.roomKitchen)
        val bathroomTv: TextView = view.findViewById(R.id.roomBathroom)
        val numberOfGuestTv: TextView = view.findViewById(R.id.roomGuest)
        val bedsLeftTv: TextView = view.findViewById(R.id.roomBedsLeft)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.room_card, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentRoomModel = mRoomModel[position]

        holder.numberTv.text = currentRoomModel.number
        holder.priceTv.text = currentRoomModel.price

        // selected room clickListener
        holder.itemView.setOnClickListener {
            navigateToDetailScreen.selectedRoom(currentRoomModel)

        }


        // change status indicator color and status text depending on room status
        when {
            currentRoomModel.bedsLeft == 0 -> {
                holder.statusTv.text = FULL
                holder.statusIndicator.setBackgroundResource(R.drawable.room_status_red)


            }
            currentRoomModel.numberOfGuest == currentRoomModel.bedsLeft -> {
                holder.statusTv.text = AVAILABLE
                holder.statusIndicator.setBackgroundResource(R.drawable.room_status_yellow)
            }
            currentRoomModel.bedsLeft != 0 && currentRoomModel.bedsLeft != currentRoomModel.numberOfGuest -> {
                holder.statusTv.text = ALMOST_FULL
                holder.statusIndicator.setBackgroundResource(R.drawable.room_status_green)
            }
        }

        // make "bed" singular or plural depending on the number of bed left
        when {
            currentRoomModel.bedsLeft > 1 -> {
                holder.bedsLeftTv.text = currentRoomModel.bedsLeft.toString() + " BedS Left"
            }
            else -> holder.bedsLeftTv.text = currentRoomModel.bedsLeft.toString() + " Bed Left"
        }

        // make "bed" singular or plural depending on the number of bed left
        when {
            currentRoomModel.numberOfGuest > 1 -> {
                holder.numberOfGuestTv.text = currentRoomModel.numberOfGuest.toString() + " Guests"
            }
            else -> holder.numberOfGuestTv.text = currentRoomModel.numberOfGuest.toString() + " Guest"
        }

        if (currentRoomModel.bathroom){
            holder.bathroomTv.visibility=View.VISIBLE
        }else holder.bathroomTv.visibility=View.GONE

        if (currentRoomModel.kitchen) {
            holder.kitchenTv.visibility = View.VISIBLE
        } else holder.kitchenTv.visibility = View.GONE


    }

    override fun getItemCount() = mRoomModel.size
    fun setData(roomData:List<RoomModel>){
        mRoomModel=roomData
    }

}

interface NavigateToDetailScreen{
    fun selectedRoom(roomModel: RoomModel)
}