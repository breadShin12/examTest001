package com.sjh.sample.data.repo

import com.sjh.sample.data.remote.base.BaseDataSource
import com.sjh.sample.data.remote.WeatherDataSource
import com.sjh.sample.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherRepoImp @Inject constructor(private val weatherDataSource: WeatherDataSource) : WeatherRepo, BaseDataSource(){
    override suspend fun getLocationList(query: String) = getResult { weatherDataSource.getWeatherLocationList(query) }
    override suspend fun getWeatherList(woeid: Int) =  getResult { weatherDataSource.getWeatherList(woeid)}
}