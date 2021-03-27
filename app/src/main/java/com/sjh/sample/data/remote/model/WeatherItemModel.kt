package com.sjh.sample.data.remote.model

import com.google.gson.annotations.SerializedName

class WeatherItemModel (
    @SerializedName("weather_state_name") val weather_state_name : String,
    @SerializedName("weather_state_abbr") val weather_state_abbr : String,
    @SerializedName("the_temp") val the_temp : Float,
    @SerializedName("humidity") val humidity : Int
)

