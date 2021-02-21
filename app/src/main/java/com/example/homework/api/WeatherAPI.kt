package com.example.homework.api


import com.example.homework.response.WeatherResponse
import retrofit2.http.GET

interface WeatherAPI {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(
        @retrofit2.http.Query("q") cityName: String
    ) : WeatherResponse
}