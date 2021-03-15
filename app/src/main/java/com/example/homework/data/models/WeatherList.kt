package com.example.homework.data.models

import com.google.gson.annotations.SerializedName

data class WeatherList(@SerializedName("list")
                       var weather_list: List<WeatherResponse>)