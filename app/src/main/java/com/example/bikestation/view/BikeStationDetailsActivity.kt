package com.example.bikestation.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bikestation.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class BikeStationDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val tvStationName: TextView = findViewById(R.id.tvStationNameDetail)
        val tvStationDistance: TextView = findViewById(R.id.tvStationDistanceDetail)
        val tvNoOfAvailableBikes: TextView = findViewById(R.id.tvNoOfAvailableBikesDetail)
        val tvNoOfAvailablePlaces: TextView = findViewById(R.id.tvNoOfAvailablePlacesDetail)
        val tvStationNo: TextView = findViewById(R.id.tvStationNoDetail)

        if (intent != null) {
            tvStationName.setText(intent.getStringExtra("label"))
            tvStationNo.setText(intent.getStringExtra("serialNo"))
            tvNoOfAvailableBikes.setText(intent.getStringExtra("bike"))
            tvNoOfAvailablePlaces.setText(intent.getStringExtra("places"))
            var lat = intent.getDoubleExtra("lat",0.0)
            var lng = intent.getDoubleExtra("lng",0.0)

            // Add a marker in Sydney and move the camera
            val sydney = LatLng(lat, lng)
            mMap.addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        }




        getSupportActionBar()?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#000000"))
        );
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        context = this@BikeStationDetailsActivity

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}




