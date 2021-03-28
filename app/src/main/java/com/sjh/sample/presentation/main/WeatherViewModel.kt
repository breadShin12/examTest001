package com.sjh.sample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sjh.sample.domain.Result
import com.sjh.sample.domain.usecase.WeatherUseCase
import com.sjh.sample.domain.entity.WeatherList
import com.sjh.sample.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    BaseViewModel() {

    private val _weatherInfo = MutableLiveData<WeatherList>()
    val weatherInfo: LiveData<WeatherList> get() = _weatherInfo

    fun requestWeatherList(query: String) {
        showProgress()
        weatherUseCase.execute(query) {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    _weatherInfo.value = it.data
                    hideProgress()
                }

                else -> hideProgress()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        weatherUseCase.unsubscribe()
    }
}