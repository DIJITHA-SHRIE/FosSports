package com.example.nrlfoxsports.View

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nrlfoxsports.Model.StatModel
import com.example.nrlfoxsports.Model.TopPlayersModel
import com.example.nrlfoxsports.databinding.TeamLayBinding
import android.R
import android.content.Intent
import com.example.nrlfoxsports.Utils.Constants


class TeamAdapter (val items : ArrayList<TopPlayersModel>, val teamId :String,val context :Context) : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TeamLayBinding.inflate(inflater,parent,false)
        return TeamViewHolder(binding.root, binding)

    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(items.get(position).short_name,items.get(position).jumper_number.toString(),items.get(position).position,context,items.get(position).id.toString(),teamId)


    }


}


class TeamViewHolder constructor(itemView: View,binding: TeamLayBinding):RecyclerView.ViewHolder(itemView){

    private var mBinding:TeamLayBinding

    init {
        mBinding = binding
    }

    fun bind(sName : String, jumper:String, rank:String,context:Context,playerId:String,teamId: String ){
        mBinding.jumper.text = jumper
        mBinding.sName.text=sName
        mBinding.position.text=rank

        Glide.with(context)
            .load("https://media.foxsports.com.au/match-centre/includes/images/headshots/landscape/nrl/$playerId.jpg")
            .into(mBinding.profileImage)

        mBinding.profileImage.setOnClickListener{
            val intent  = Intent(context,PlayerActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(Constants.TEAMID,teamId)
            intent.putExtra(Constants.PLAYERID,playerId)
            context.startActivity(intent)
        }


    }


}