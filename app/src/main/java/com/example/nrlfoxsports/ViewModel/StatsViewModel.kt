package com.example.nrlfoxsports.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nrlfoxsports.Model.PlayerExclu
import com.example.nrlfoxsports.Model.StatModel
import com.example.nrlfoxsports.Repository.DataRepository
import org.koin.dsl.module.applicationContext
import org.koin.standalone.KoinComponent

class StatsViewModel(val dataRepository: DataRepository):ViewModel(),KoinComponent {

    var statresponseData = MutableLiveData<ArrayList<StatModel>>()
    var playerExcluResponseeData = MutableLiveData<PlayerExclu>()
    var toastError = MutableLiveData<String>()



    init {
        statresponseData.value
        playerExcluResponseeData.value
        toastError.value
    }



    fun getStatData(){


        dataRepository.fetchStatData(object : DataRepository.PlayerStatsData {
            override fun onSuccess(data: ArrayList<StatModel>) {

                statresponseData.value = data

            }

            override fun onFailure(message:String) {
                Log.i("ThrowableStat",message)
                //REQUEST FAILED
            }

            override fun onToast(error: String) {
                toastError.value=error
            }
        })



    }

    fun getPlayerExcluData(teamId:String, playertId:String){


        dataRepository.fetchPlayerLastMatchDetails(object : DataRepository.PlayerExcluData {
            override fun onSuccess(data: PlayerExclu) {

                playerExcluResponseeData.value = data

            }

            override fun onFailure(message:String) {
                Log.i("ThrowableStat",message)
                //REQUEST FAILED
            }

            override fun onToast(error: String) {
                toastError.value = error


            }
        },teamId,playertId)



    }

}