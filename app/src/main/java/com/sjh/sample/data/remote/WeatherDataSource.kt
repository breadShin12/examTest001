package com.sjh.sample.data.remote

import com.sjh.sample.data.remote.model.WeatherLocationModel
import com.sjh.sample.data.remote.model.WeatherModel
import retrofit2.Response
import javax.inject.Inject

class WeatherDataSource @Inject constructor(private val apiService: WeatherApiService) {

    suspend fun getWeatherLocationList(query : String)
    : Response<List<WeatherLocationModel>> = apiService.getLocationList(query)

    suspend fun getWeatherList(woeid : Int)
            : Response<WeatherModel> = apiService.getWeatherList(woeid)
}