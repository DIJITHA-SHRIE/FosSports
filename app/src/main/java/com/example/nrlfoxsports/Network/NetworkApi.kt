package com.example.nrlfoxsports.Network

import com.example.nrlfoxsports.Model.PlayerExclu
import com.example.nrlfoxsports.Model.StatModel
import retrofit2.Call
import retrofit2.http.*

interface NetworkApi {


    @GET("/3.0/api/sports/league/matches/NRL20190101/topplayerstats.json;type={fantasy_points};" +
            "type={tackles};type={runs};type={run_metres}?")
    fun getStatsAPI(@Path("fantasy_points") fantasy_points:String,
                    @Path("tackles")  tackles:String,
                    @Path("runs") runs:String,
                    @Path("run_metres")run_metres:String,
                    @Query("limit") limit:String,
                    @Query("userkey")userkey:String
                    ): Call<ArrayList<StatModel>>


    @GET("/3.0/api/sports/league/series/1/seasons/117/teams/{teamID}/players/{playerID}/detailedstats.json?")
    fun getPlayerlastmatchAPI(@Path ("teamID") teamID:String,
                              @Path("playerID") playerID:String,
                              @Query("userkey")userkey:String
                              ):Call<PlayerExclu>


}