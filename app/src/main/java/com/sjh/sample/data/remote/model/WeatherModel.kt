package com.sjh.sample.data.remote.model

import com.google.gson.annotations.SerializedName
import com.sjh.sample.domain.entity.WeatherEntity
import com.sjh.sample.domain.entity.WeatherItemEntity

data class WeatherModel(
    @SerializedName("consolidated_weather") val consolidated_weather : List<WeatherItemModel>
)

fun WeatherModel.mapToDomain() = WeatherEntity(consolidated_weather.mapToDomain())

fun List<WeatherItemModel>.mapToDomain(): List<WeatherItemEntity> =
    map { WeatherItemEntity(it.weather_state_name, it.weather_state_abbr, it.the_temp, it.humidity)}

