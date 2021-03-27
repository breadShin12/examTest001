package com.sjh.sample.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sjh.sample.R
import com.sjh.sample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var listAdapter : WeatherListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        init()
        setUI()
    }

    private fun init(){
        with(binding){
            lifecycleOwner = this@MainActivity
            vm = weatherViewModel
        }

        weatherViewModel.weatherInfo.observe(this@MainActivity, {
            listAdapter.setList(it)
        })

        requestWeather()
    }

    private fun setUI(){
        with(binding){
            rv.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                listAdapter = WeatherListAdapter().also {
                    adapter = it
                }
            }

            sl.setOnRefreshListener {
                requestWeather()
                sl.isRefreshing = false
            }
        }
    }

    private fun requestWeather(){
        weatherViewModel.requestWeatherList("se")
    }
}