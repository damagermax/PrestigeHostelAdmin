package com.example.prestigehosteladmin.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.prestigehosteladmin.model.RoomModel
import com.example.prestigehosteladmin.utils.Constants.ROOMS_REF
import com.example.prestigehosteladmin.utils.Constants.ROOM_SUCCESS_MSG
import com.example.prestigehosteladmin.utils.Response
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RoomRepository(
    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val roomsRef: DatabaseReference = rootRef.child(ROOMS_REF),
    private val room_ID: String = System.currentTimeMillis().toString()

) {

    fun addRoom(room: HashMap<String, Any>): MutableLiveData<Response> {
        val addRoomMutableLiveData = MutableLiveData<Response>()
        room["id"] = room_ID
        roomsRef.child(room_ID).setValue(room).addOnCompleteListener { task ->

            val response = Response()
            response.loading = true

            if (task.isSuccessful) {
                response.loading = false
                response.message = ROOM_SUCCESS_MSG

            } else {
                response.loading = false
                response.exception = task.exception

            }
            addRoomMutableLiveData.value = response


        }
        return addRoomMutableLiveData
    }

    fun updateRoom(updatedRoom: HashMap<String, Any>): MutableLiveData<Response> {
        val updateRoomMutableLiveData = MutableLiveData<Response>()
        roomsRef.child(updatedRoom["id"].toString()).updateChildren(updatedRoom)
            .addOnCompleteListener { task ->

                val response = Response()
                response.loading = true

                if (task.isSuccessful) {
                    response.loading = false
                    response.message = ROOM_SUCCESS_MSG

                } else {
                    response.loading = false
                    response.exception = task.exception
                }
                updateRoomMutableLiveData.value = response
            }
        return updateRoomMutableLiveData
    }

    fun deleteRoom(iD: String): MutableLiveData<Response> {
        val deleteRoomMutableLiveData = MutableLiveData<Response>()
        roomsRef.child(iD).removeValue().addOnCompleteListener { task ->
            val response = Response()
            response.loading = true

            if (task.isSuccessful) {
                response.loading = false
                response.message = ROOM_SUCCESS_MSG

            } else {
                response.loading = false
                response.exception = task.exception
            }
            deleteRoomMutableLiveData.value = response

        }

        return deleteRoomMutableLiveData
    }

    fun getRooms(): MutableLiveData<Response> {
        val roomsMutableLiveData = MutableLiveData<Response>()
        roomsRef.get().addOnCompleteListener { task ->

            val response = Response()

            if (task.isSuccessful) {
                val result = task.result
                result.let { dataSnapshot ->
                    response.rooms = dataSnapshot.children.map { snapshot ->
                        snapshot.getValue(RoomModel::class.java)!!
                    }
                }
            } else {
                response.exception = task.exception
            }
            roomsMutableLiveData.value = response
        }
        return roomsMutableLiveData
    }
}