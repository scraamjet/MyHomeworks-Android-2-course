package com.example.homework.data.repository

import com.example.homework.data.api.ApiFactory
import com.example.homework.data.api.entity.WeatherResponse

class RemoteRepository {
    private val weatherAPI = ApiFactory.weatherAPI

    suspend fun getWeatherList(latitude: Double, longitude: Double): List<WeatherResponse>{
        return weatherAPI.getWeatherList(latitude , longitude, 30).weather_list
    }
    suspend fun getWeatherByName(name:String): WeatherResponse {
        return weatherAPI.getWeatherByName(name)
    }
    suspend fun getWeatherById(id:Int): WeatherResponse {
        return weatherAPI.getWeatherById(id)
    }
}