package com.example.nrlfoxsports.View

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nrlfoxsports.databinding.ActivityPlayerBinding
import com.example.nrlfoxsports.databinding.LastmatchlayBinding
import com.example.nrlfoxsports.databinding.StatsAdapLayBinding

class PlayerLastMatchAdapter(val  itemsKey:ArrayList<String>,val itemsVal:ArrayList<String>,val context: Context) :RecyclerView.Adapter<LastMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LastmatchlayBinding.inflate(inflater,parent,false)
        return LastMatchViewHolder(binding.root, binding)


    }

    override fun getItemCount(): Int = itemsKey.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {


        holder.bind(itemsKey.get(position).replace("_"," ").toUpperCase(),itemsVal.get(position))
    }

}

class LastMatchViewHolder constructor(itemView: View, binding: LastmatchlayBinding):RecyclerView.ViewHolder(itemView){
    private var mBinding:LastmatchlayBinding
    init{
        mBinding=binding
    }
    fun bind(lastmatchkey:String,lastmatchval:String){
        mBinding.lastmatchkey.text = lastmatchkey
        mBinding.lastmatchvalue.text=lastmatchval

    }
}