package com.example.nrlfoxsports.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nrlfoxsports.Model.StatModel
import com.example.nrlfoxsports.Model.TopPlayersModel
import com.example.nrlfoxsports.databinding.StatsAdapLayBinding

class StatAdapter (val items : ArrayList<StatModel>, val context:Context) : RecyclerView.Adapter<StatViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StatsAdapLayBinding.inflate(inflater,parent,false)
        return StatViewHolder(binding.root, binding)

    }

    override fun getItemCount(): Int = 4
    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        holder.bind(items.get(position).stat_type.replace("_"," ").toUpperCase(),
            items.get(position).team_A.top_players,items.get(position).team_B.top_players,items.get(position).team_A.id.toString(),context)


    }




}


class StatViewHolder constructor(itemView: View, binding: StatsAdapLayBinding): RecyclerView.ViewHolder(itemView){

    private var mBinding: StatsAdapLayBinding

    init {
        mBinding = binding
    }
    fun bind(statType:String, teamAplayers:ArrayList<TopPlayersModel>, teamBplayers : ArrayList<TopPlayersModel>,teamId:String,context: Context){
        mBinding.statType.text = statType

        mBinding.teamArv!!.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)

        mBinding.teamBrv!!.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)



        var teamAdapter: TeamAdapter = TeamAdapter(teamAplayers,teamId,context)
        mBinding.teamArv.adapter = teamAdapter


        var teamBdapter: TeamAdapter = TeamAdapter(teamBplayers,teamId,context)
        mBinding.teamBrv.adapter = teamBdapter

    }




}