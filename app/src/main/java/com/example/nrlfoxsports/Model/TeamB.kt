package com.example.nrlfoxsports.Model

import java.io.Serializable

data class TeamB(
    var id:Int,
    var name:String,
    var code:String,
    var short_name:String,
    var top_players:ArrayList<TopPlayersModel>
):Serializable