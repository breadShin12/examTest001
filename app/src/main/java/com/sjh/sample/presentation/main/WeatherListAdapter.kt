package com.sjh.sample.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sjh.sample.R
import com.sjh.sample.data.remote.model.WeatherItemModel
import com.sjh.sample.data.remote.model.WeatherLocationModel
import com.sjh.sample.databinding.RowWeatherBinding
import com.sjh.sample.domain.entity.WeatherItemEntity
import com.sjh.sample.domain.entity.WeatherList
import com.sjh.sample.domain.entity.WeatherLocationEntity

typealias WeatherData = Pair<WeatherLocationEntity, List<WeatherItemEntity>>
class WeatherListAdapter :
    ListAdapter<WeatherData, WeatherListAdapter.WeatherViewHolder>(diffCallback)
{
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<WeatherData>() {
            override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
                oldItem.first.woeid == oldItem.first.woeid

            override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
                oldItem.first == oldItem.first
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val bind: RowWeatherBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_weather,
            parent,
            false
        )
        return WeatherViewHolder(bind)
    }

    override fun onBindViewHolder(holder: WeatherListAdapter.WeatherViewHolder, position: Int) {
        val weatherData  = getItem(position)
        holder.bind(weatherData.first, weatherData.second[0], weatherData.second[1])
    }

    override fun getItemCount(): Int = currentList.size

    fun setList(weatherList : WeatherList){
        submitList(weatherList.list)
    }

    inner class WeatherViewHolder(private val bind: RowWeatherBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(weatherLocationEntity : WeatherLocationEntity, today: WeatherItemEntity, tomorrow: WeatherItemEntity) {
            with(bind){
                location = weatherLocationEntity
                todayWeather.weather = today
                tomorrowWeather.weather = tomorrow
                executePendingBindings()
            }
        }
    }
}


