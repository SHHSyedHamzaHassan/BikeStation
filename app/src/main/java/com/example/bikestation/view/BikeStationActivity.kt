package com.example.bikestation.view

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bikestation.R
import com.example.bikestation.adapter.BikeStationAdapter
import com.shh.bikestations.viewmodel.BikeStationViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.drawable.ColorDrawable


class BikeStationActivity : AppCompatActivity() {

    lateinit var context: Context

    lateinit var bikeStationViewModel: BikeStationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getSupportActionBar()?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#000000")));
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        context = this@BikeStationActivity

        bikeStationViewModel = ViewModelProvider(this).get(BikeStationViewModel::class.java)


        val recyclerview = findViewById<RecyclerView>(R.id.rvRecycleView)
        recyclerview.layoutManager = LinearLayoutManager(this)





        wp7progressBar.showProgressBar()

        bikeStationViewModel.getBikeStationIno()!!.observe(this, Observer { BikeStationModel ->

            wp7progressBar.hideProgressBar()

            val adapter = BikeStationAdapter(BikeStationModel.features)
            adapter.setContext(this@BikeStationActivity)
            recyclerview.adapter = adapter


        })


    }
}
