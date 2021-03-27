package com.sjh.sample.data.remote
import com.sjh.sample.data.remote.model.WeatherLocationModel
import com.sjh.sample.data.remote.model.WeatherModel
import retrofit2.Response
import retrofit2.http.*


interface WeatherApiService {
    @GET("/api/location/search")
    suspend fun getLocationList(
        @Query("query") query: String,
    ): Response<List<WeatherLocationModel>>

    @GET("/api/location/{woeid}")
    suspend fun getWeatherList(
        @Path("woeid") woeid: Int
    ): Response<WeatherModel>
}
