package com.sjh.sample.domain.repo

import com.sjh.sample.domain.Result
import com.sjh.sample.data.remote.model.WeatherLocationModel
import com.sjh.sample.data.remote.model.WeatherModel

interface WeatherRepo {
    suspend fun getLocationList(query : String) : Result<List<WeatherLocationModel>>
    suspend fun getWeatherList(woeid: Int) : Result<WeatherModel>
}