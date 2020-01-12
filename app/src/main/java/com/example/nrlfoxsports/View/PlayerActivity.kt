package com.example.nrlfoxsports.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nrlfoxsports.Model.PlayerExclu

import com.example.nrlfoxsports.Utils.Constants
import com.example.nrlfoxsports.ViewModel.StatsViewModel

import com.example.nrlfoxsports.databinding.ActivityPlayerBinding

import org.koin.android.viewmodel.ext.android.viewModel


import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.Exception
import org.json.JSONObject


class PlayerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPlayerBinding
    private val statsViewModel: StatsViewModel by viewModel()
    var lastMatchMapKey = ArrayList<String>()
    var lastMatchMapValue = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.nrlfoxsports.R.layout.activity_player)

        binding =
            DataBindingUtil.setContentView(this, com.example.nrlfoxsports.R.layout.activity_player)
        binding.setLifecycleOwner(this)

        val bundle: Bundle? = intent.extras
        val teamId = bundle!!.get(Constants.TEAMID)
        val playerId = bundle!!.get(Constants.PLAYERID)


        Log.i("TEAMPLAYER", "$teamId then $playerId")

        statsViewModel.getPlayerExcluData(teamId.toString(), playerId.toString())
        binding.progressBar.visibility = View.VISIBLE

        statsViewModel.playerExcluResponseeData.observe(
            this,
            Observer(function = fun(playerExcluObj: PlayerExclu?) {
                playerExcluObj?.let {
                    binding.progressBar.visibility = View.GONE

                    Log.i("PlayerExcluData", playerExcluObj.date_of_birth)

                    Glide.with(this)
                        .load("https://media.foxsports.com.au/match-centre/includes/images/headshots/landscape/nrl/$playerId.jpg")
                        .into(binding.profilebig)
                    binding.namebig.text = playerExcluObj.full_name
                    binding.positionbig.text = playerExcluObj.position


                    val ow = ObjectMapper().writer().withDefaultPrettyPrinter()
                    val jsonStr = ow.writeValueAsString(playerExcluObj.last_match_stats)

                    Log.i("json", jsonStr)


                    try {

                        val obj = JSONObject(jsonStr)
                        var iter: Iterator<String>
                        iter = obj.keys()
                        while (iter.hasNext()) {
                            var key: String = iter.next()
                            try {
                                var keyValue = obj.get(key)

                                lastMatchMapKey.add(key)
                                lastMatchMapValue.add(keyValue.toString())

                              /*  val map = HashMap<String, String>()
                                map.put(key, keyValue.toString())
                                lastMatchMap.add(map)
                               */

                            } catch (e: Exception) {

                            }
                        }

                        Log.i("LastMatchMap", lastMatchMapKey.size.toString())

                        binding.lastmatchrv!!.layoutManager = LinearLayoutManager(this,
                            LinearLayoutManager.VERTICAL,false)
                        var lastmatchAdapter: PlayerLastMatchAdapter = PlayerLastMatchAdapter(lastMatchMapKey,lastMatchMapValue,applicationContext)
                        binding.lastmatchrv.adapter = lastmatchAdapter





                    } catch (e: Exception) {

                    }


                }
            })
        )



      statsViewModel.toastError.observe(this, Observer { res->
          if(res!=null){
              binding.progressBar.visibility = View.GONE
              Toast.makeText(applicationContext,res,Toast.LENGTH_LONG).show()
          }

      })


    }
}
