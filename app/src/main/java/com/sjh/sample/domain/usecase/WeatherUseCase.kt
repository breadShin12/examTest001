package com.sjh.sample.domain.usecase

import com.sjh.sample.domain.extensions.parallelMap
import com.sjh.sample.data.remote.model.mapToDomain
import com.sjh.sample.domain.Result
import com.sjh.sample.domain.base.BaseUseCase
import com.sjh.sample.domain.entity.WeatherItemEntity
import com.sjh.sample.domain.entity.WeatherLocationEntity
import com.sjh.sample.domain.repo.WeatherRepo
import com.sjh.sample.domain.entity.WeatherList

class WeatherUseCase (private val weatherRepo: WeatherRepo)
    : BaseUseCase<WeatherList, String>() {
    //suspend fun getLocationList(query : String) = weatherRepo.getLocationList(query)
    //suspend fun getWeatherList(woeid : Int) = weatherRepo.getWeatherList(woeid)

    override suspend fun executeOnBackground(params: String): Result<WeatherList> {
        val weatherList: MutableList<Pair<WeatherLocationEntity, List<WeatherItemEntity>>> = mutableListOf()
        val locationResult = weatherRepo.getLocationList(params)
        return when (locationResult.status) {
            Result.Status.SUCCESS -> {
                locationResult.data?.parallelMap { location ->
                    weatherList.add(Pair(location.mapToDomain(), getWeatherInfo(location.woeid)))
                }
                Result.success(WeatherList(weatherList))
            }

            else -> {
                Result.error(locationResult.message ?: "error", null)
            }
        }
    }

    /**
     * Location 검색후 woeid로 해당 지역의 날씨 리스트를 받아옴.
     */
    private suspend fun getWeatherInfo(woeid: Int): List<WeatherItemEntity> {
        var weatherModel: List<WeatherItemEntity> = listOf()
        try {
            weatherRepo.getWeatherList(woeid).let {
                if (it.status == Result.Status.SUCCESS)
                    weatherModel = it.data?.consolidated_weather?.mapToDomain() ?: listOf()
            }

        } catch (e: Exception) {
            e.stackTrace
        }
        return weatherModel
    }
}