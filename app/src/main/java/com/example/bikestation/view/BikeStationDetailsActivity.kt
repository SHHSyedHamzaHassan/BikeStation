package com.example.bikestation.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.bikestation.R
import android.graphics.drawable.BitmapDrawable

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.DrawableCompat

import android.os.Build

import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdate


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


        getSupportActionBar()?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#000000"))
        );
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        context = this@BikeStationDetailsActivity

        val tvStationName: TextView = findViewById(R.id.tvStationNameDetail)
        val tvStationDistance: TextView = findViewById(R.id.tvStationDistanceDetail)
        val tvNoOfAvailableBikes: TextView = findViewById(R.id.tvNoOfAvailableBikesDetail)
        val tvNoOfAvailablePlaces: TextView = findViewById(R.id.tvNoOfAvailablePlacesDetail)
        val tvStationNo: TextView = findViewById(R.id.tvStationNoDetail)

        if (intent != null) {
            tvStationName.setText(intent.getStringExtra("label"))
            tvStationNo.setText(intent.getStringExtra("serialNo") + " ")
            tvNoOfAvailableBikes.setText(intent.getStringExtra("bike"))
            tvNoOfAvailablePlaces.setText(intent.getStringExtra("places"))
            var lat = intent.getDoubleExtra("lat", 0.0)
            var lng = intent.getDoubleExtra("lng", 0.0)

            // Add a marker in Sydney and move the camera
            val station = LatLng(lat, lng)


            mMap.addMarker(
                MarkerOptions()
                    .position(station)
                    .title(intent.getStringExtra("bike"))
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            getBitmapFromVectorDrawable(
                                context,
                                R.drawable.ic_bike_icon
                            )
                        )
                    )
            )?.showInfoWindow()
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(station))
            val location = CameraUpdateFactory.newLatLngZoom(
                station, 10f
            )
            mMap.animateCamera(location)


        }


    }

    fun getBitmapFromVectorDrawable(context: Context?, drawableId: Int): Bitmap {
        var drawable = ContextCompat.getDrawable(context!!, drawableId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable!!).mutate()
        }
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
}




