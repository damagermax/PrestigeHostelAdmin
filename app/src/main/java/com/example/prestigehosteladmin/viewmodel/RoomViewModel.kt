package com.example.prestigehosteladmin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.prestigehosteladmin.repository.RoomRepository
import com.example.prestigehosteladmin.utils.Response

class RoomViewModel(
    private val roomRepository: RoomRepository = RoomRepository()
) : ViewModel() {

    fun addRoom(newRoom: HashMap<String, Any>): LiveData<Response> {
        return roomRepository.addRoom(newRoom)
    }

    fun updateRoom(updatedRoom: HashMap<String, Any>): LiveData<Response> {
        return roomRepository.updateRoom(updatedRoom)
    }

    fun deleteRoom(iD: String): LiveData<Response> {
        return roomRepository.deleteRoom(iD)
    }

    fun getRooms():LiveData<Response>{
        return roomRepository.getRooms()
    }


}