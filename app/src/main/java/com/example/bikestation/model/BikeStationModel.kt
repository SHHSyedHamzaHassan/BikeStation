package com.shh.bikestations.model

data class BikeStationModel(
    val crs: Crs,
    val features: List<Feature>,
    val type: String
)