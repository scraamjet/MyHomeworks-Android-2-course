package com.example.homework.response.models

import com.example.homework.response.WeatherResponse
import com.google.gson.annotations.SerializedName

data class WeatherList(@SerializedName("list")
                       var weather_list: List<WeatherResponse>)