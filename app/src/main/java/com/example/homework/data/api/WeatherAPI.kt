package com.example.homework.data.api


import com.example.homework.data.models.WeatherResponse
import com.example.homework.data.models.WeatherList
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    suspend fun getWeatherByName(
        @retrofit2.http.Query("q") cityName: String
    ) : WeatherResponse
    @GET("find")
    suspend fun getWeatherList(@Query("lat") latitude : Double,
                              @Query("lon") longitude : Double,
                              @Query("cnt") cnt: Int ): WeatherList
    @GET("weather")
    suspend fun getWeatherById(@Query("id") id: Int): WeatherResponse
}