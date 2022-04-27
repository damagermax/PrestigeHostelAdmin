package com.example.prestigehosteladmin.model

data class RoomModel(
    val number:String="",
    val price:String="",
    val numberOfGuest:Int=0,
    val bedsLeft:Int=0,
    val kitchen:Boolean=false,
    val bathroom:Boolean=false
)
