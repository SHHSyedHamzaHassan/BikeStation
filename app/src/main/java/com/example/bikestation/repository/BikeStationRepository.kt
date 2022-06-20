package com.shh.bikestations.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shh.bikestations.model.BikeStationModel
import com.shh.bikestations.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BikeStationRepository {

    val bikeStationData = MutableLiveData<BikeStationModel>()

    fun getServicesApiCall(): MutableLiveData<BikeStationModel> {

        val call = RetrofitClient.apiInterface.getBikeStationsInfo()

        call.enqueue(object : Callback<BikeStationModel> {
            override fun onFailure(call: Call<BikeStationModel>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<BikeStationModel>,
                response: Response<BikeStationModel>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()



                bikeStationData.value = data!!
            }
        })

        return bikeStationData
    }
}