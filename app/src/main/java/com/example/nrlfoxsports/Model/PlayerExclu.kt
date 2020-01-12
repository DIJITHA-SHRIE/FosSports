package com.example.nrlfoxsports.Model

data class PlayerExclu(
     var id:Int,
     var surname:String,
     var position:String,
     var full_name:String,
     var short_name:String,
     var date_of_birth:String,
     var height_cm:Int,
     var other_names:String,
     var weight_kg:Int,
     var last_match_id:String,
     var last_match_stats:LastMatchStats

)
