package com.example.nrlfoxsports.Model

import java.io.Serializable

data class TopPlayersModel(
    var id:Int,
    var position:String,
    var full_name:String,
    var short_name:String,
    var stat_value:Int,
    var jumper_number:Int

):Serializable