package com.example.homework.api

import com.example.homework.response.WeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiFactory {
    private const val QUERY_API_KEY = "56fc6c6cb76c0864b4cd055080568268"
    private const val QUERY_UNITS = "metric"
    private const val QUERY_LANG = "eng"
    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
                .addQueryParameter("appid", QUERY_API_KEY )
                .build()
                .let {
                    chain.proceed(
                            original.newBuilder().url(it).build()
                    )
                }
    }

    private val metricInterceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
            .addQueryParameter("lang", QUERY_LANG)
            .addQueryParameter("units", QUERY_UNITS)
            .build()
            .let {
                chain.proceed(
                     original.newBuilder().url(it).build()
            )
        }
    }

    private val client by lazy {
        OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(LoggingInterceptor())
                .addInterceptor(metricInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val weatherAPI: WeatherAPI by lazy {
        retrofit.create(WeatherAPI::class.java)
    }

    suspend fun getWeatherList(latitude: Double, longitude: Double): List<WeatherResponse>{
        return weatherAPI.getWeatherList(latitude , longitude, 30).weather_list
    }
    suspend fun getWeatherByName(name:String):WeatherResponse{
        return weatherAPI.getWeatherByName(name)
    }
    suspend fun getWeatherById(id:Int):WeatherResponse{
        return weatherAPI.getWeatherById(id)
    }

}