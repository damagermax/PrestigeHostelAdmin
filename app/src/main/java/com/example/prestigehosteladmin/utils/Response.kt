package com.example.prestigehosteladmin.utils

import com.example.prestigehosteladmin.model.RoomModel
import java.lang.Exception

data class Response(
    var rooms: List<RoomModel>? = null,
    var loading: Boolean = false,
    var message:String? = null,
    var exception: Exception? = null
)
