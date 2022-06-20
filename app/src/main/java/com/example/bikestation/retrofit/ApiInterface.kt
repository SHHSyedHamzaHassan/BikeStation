package com.shh.bikestations.retrofit

import com.shh.bikestations.model.BikeStationModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("map_service.html?mtype=pub_transport&co=stacje_rowerowe")
    fun getBikeStationsInfo() : Call<BikeStationModel>
}