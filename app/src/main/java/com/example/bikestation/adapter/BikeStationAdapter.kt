package com.example.bikestation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bikestation.R
import com.shh.bikestations.model.Feature
import android.content.Intent
import android.widget.LinearLayout
import com.example.bikestation.view.BikeStationDetailsActivity


class BikeStationAdapter(private val mList: List<Feature>) :
    RecyclerView.Adapter<BikeStationAdapter.ViewHolder>() {

    private var context: Context? = null

    fun setContext(context: Context?) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_bike_station, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        var stationNumber: Int = position + 1
        holder.tvStationNo.setText("" + stationNumber + " ")

        holder.tvStationName.setText(ItemsViewModel.properties.label)
        holder.tvNoOfAvailableBikes.setText(ItemsViewModel.properties.bikes)
        holder.tvNoOfAvailablePlaces.setText(ItemsViewModel.properties.free_racks)

        holder.lyRow.setOnClickListener(View.OnClickListener {
            val intent: Intent =
                Intent(context, BikeStationDetailsActivity::class.java)
                    .putExtra("label", ItemsViewModel.properties.label)
                    .putExtra("serialNo", "" + stationNumber)
                    .putExtra("bike", "" + ItemsViewModel.properties.bikes)
                    .putExtra("places", "" + ItemsViewModel.properties.free_racks)
                    .putExtra("lat", ItemsViewModel.geometry.coordinates.get(0))
                    .putExtra("lng", ItemsViewModel.geometry.coordinates.get(1))
            context?.startActivity(intent)
        })


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvStationName: TextView = itemView.findViewById(R.id.tvStationName)
        val tvStationDistance: TextView = itemView.findViewById(R.id.tvStationDistance)
        val tvNoOfAvailableBikes: TextView = itemView.findViewById(R.id.tvNoOfAvailableBikes)
        val tvNoOfAvailablePlaces: TextView = itemView.findViewById(R.id.tvNoOfAvailablePlaces)
        val tvStationNo: TextView = itemView.findViewById(R.id.tvStationNo)
        val lyRow: LinearLayout = itemView.findViewById(R.id.lyRow)
    }
}