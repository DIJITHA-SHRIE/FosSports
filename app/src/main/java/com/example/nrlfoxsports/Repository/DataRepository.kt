package com.example.nrlfoxsports.Repository

import android.os.Handler
import android.widget.Toast
import com.example.nrlfoxsports.Model.PlayerExclu
import com.example.nrlfoxsports.Model.StatModel
import com.example.nrlfoxsports.Network.NetworkApi
import org.koin.dsl.module.applicationContext
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class DataRepository (val networkApi: NetworkApi) {

       fun fetchStatData(playerStatsData: PlayerStatsData){
        networkApi.getStatsAPI("fantasy_points","tackles","runs","run_metres",
            "5","A00239D3-45F6-4A0A-810C-54A347F144C2").enqueue(object:retrofit2.Callback<ArrayList<StatModel>>{
            override fun onFailure(call: Call<ArrayList<StatModel>>, t: Throwable) {
                playerStatsData.onFailure(t.localizedMessage)

            }

            override fun onResponse(call: Call<ArrayList<StatModel>>, response: Response<ArrayList<StatModel>>) {
                try {
                    playerStatsData.onSuccess(response.body() as ArrayList<StatModel>)
                }
                catch (e:Exception){
                    playerStatsData.onToast("Url Not Found!! please try after sometime")
                }


            }

        })

    }


    interface PlayerStatsData{
        fun onSuccess(data:ArrayList<StatModel>)
        fun onFailure(message:String)
        fun onToast(error:String)

    }

    fun fetchPlayerLastMatchDetails(playerExclu: PlayerExcluData,teamId:String, playerId:String){
        networkApi.getPlayerlastmatchAPI(teamId,playerId, "A00239D3-45F6-4A0A-810C-54A347F144C2").
            enqueue(object:retrofit2.Callback<PlayerExclu>{
            override fun onFailure(call: Call<PlayerExclu>, t: Throwable) {
                playerExclu.onFailure(t.localizedMessage)

            }

            override fun onResponse(call: Call<PlayerExclu>, response: Response<PlayerExclu>) {
                try {
                    playerExclu.onSuccess(response.body() as PlayerExclu )
                }
                catch (e:Exception){
                    playerExclu.onToast("Url Not Found!! please try after sometime")

                }


            }

        })

    }


    interface PlayerExcluData{
        fun onSuccess(data:PlayerExclu)
        fun onFailure(message:String)
        fun onToast(error:String)

    }



}