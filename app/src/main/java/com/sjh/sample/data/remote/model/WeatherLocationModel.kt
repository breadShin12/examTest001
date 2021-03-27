package com.sjh.sample.data.remote.model

import com.google.gson.annotations.SerializedName
import com.sjh.sample.domain.entity.WeatherLocationEntity

data class WeatherLocationModel(
    @SerializedName("title") val title : String,
    @SerializedName("location_type") val location_type : String,
    @SerializedName("woeid") val woeid : Int,
    @SerializedName("latt_long") val latt_long : String
)

fun WeatherLocationModel.mapToDomain() = WeatherLocationEntity(title, location_type, woeid, latt_long)