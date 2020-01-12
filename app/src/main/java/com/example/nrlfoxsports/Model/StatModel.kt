package com.example.nrlfoxsports.Model

import java.io.Serializable

data class StatModel (
    var match_id:String,
    var team_A:TeamA,
    var team_B:TeamB,
    var stat_type:String

):Serializable