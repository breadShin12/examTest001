package com.sjh.sample.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun showProgress() {
        _isLoading.value = true
        //Log.d("test", "showProgress")
    }

    fun hideProgress() {
        _isLoading.value = false
        //Log.d("test", "hideProgress")
    }
}