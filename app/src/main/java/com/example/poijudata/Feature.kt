package com.example.poijudata

data class Feature(
    val geometry: Geometry,
    val properties: Properties,
    val siteNumber: Int,
    val type: String
)