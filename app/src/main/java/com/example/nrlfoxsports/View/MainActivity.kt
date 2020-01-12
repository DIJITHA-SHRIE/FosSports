package com.example.nrlfoxsports.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nrlfoxsports.Model.StatModel
import com.example.nrlfoxsports.R
import com.example.nrlfoxsports.ViewModel.StatsViewModel
import com.example.nrlfoxsports.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding

    private val statsViewModel: StatsViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)

        binding.statRecyclerView!!.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)



        statsViewModel.getStatData()
        binding.progressBar.visibility = View.VISIBLE

        statsViewModel.statresponseData.observe(this, Observer(function = fun(statList:  ArrayList<StatModel>?) {
            statList?.let {
                binding.progressBar.visibility = View.GONE

                Log.i("StatmatchId",statList.get(0).match_id)
                var statListAdapter: StatAdapter = StatAdapter(statList,applicationContext)
                binding.statRecyclerView.adapter = statListAdapter



            }
        }))

        statsViewModel.toastError.observe(this, Observer { res->
            if(res!=null){
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext,res, Toast.LENGTH_LONG).show()
            }

        })


    }
}
