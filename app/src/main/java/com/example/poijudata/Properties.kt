package com.example.poijudata

data class Properties(
    val confidence: String,
    val heelAngle: Double,
    val lastUpdate: String,
    val lightStatus: String,
    val seaState: String,
    val siteName: String,
    val siteNumber: Int,
    val siteType: String,
    val temperature: Int,
    val trend: String,
    val windWaveDir: Int
)