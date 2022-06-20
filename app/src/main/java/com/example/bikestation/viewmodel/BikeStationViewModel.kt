package com.shh.bikestations.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shh.bikestations.model.BikeStationModel
import com.shh.bikestations.repository.BikeStationRepository

class BikeStationViewModel  : ViewModel() {

    var servicesLiveData: MutableLiveData<BikeStationModel>? = null

    fun getBikeStationIno() : LiveData<BikeStationModel>? {
        servicesLiveData = BikeStationRepository.getServicesApiCall()
        return servicesLiveData
    }

}