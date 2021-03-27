package com.sjh.sample.domain.entity

data class WeatherList(val list: MutableList<Pair<WeatherLocationEntity, List<WeatherItemEntity>>>)