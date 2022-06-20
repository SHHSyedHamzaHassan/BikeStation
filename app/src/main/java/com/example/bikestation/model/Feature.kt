package com.shh.bikestations.model

import java.io.Serializable

data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: PropertiesX,
    val type: String
) : Serializable