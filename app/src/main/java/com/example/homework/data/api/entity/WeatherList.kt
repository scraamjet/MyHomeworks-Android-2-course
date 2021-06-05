package com.example.homework.data.api.entity

import com.google.gson.annotations.SerializedName

data class WeatherList(@SerializedName("list")
                       var weather_list: List<WeatherResponse>)