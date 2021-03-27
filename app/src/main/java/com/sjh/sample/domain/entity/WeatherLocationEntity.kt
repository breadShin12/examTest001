package com.sjh.sample.domain.entity

data class WeatherLocationEntity(
    val title : String,
    val location_type : String,
    val woeid : Int,
    val latt_long : String
)
