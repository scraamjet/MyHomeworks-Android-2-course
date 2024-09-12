package com.example.homework

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.homework.api.ApiFactory
import com.example.homework.helpers.PressureConverter
import com.example.homework.helpers.TemperatureConverter
import com.example.homework.helpers.WindConverter
import com.example.homework.services.*
import kotlinx.android.synthetic.main.activity_weather_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Field
import kotlin.coroutines.CoroutineContext

const val BASE_IMAGE_URI = "http://openweathermap.org/img/wn/"

class WeatherInfoActivity : AppCompatActivity(),CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    private lateinit var temperatureConverter: TemperatureConverter
    private lateinit var windConverter : WindConverter
    private lateinit var pressureConverter : PressureConverter
    private lateinit var backgroundDrawableHelper : BackgroundDrawableHelper

    private lateinit var constraintLayoutWeatherInfoActivity: ConstraintLayout

    private lateinit var backgroundDrawableName: String
    private lateinit var iconUri:Uri

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        temperatureConverter = TemperatureConverter()
        windConverter = WindConverter()
        pressureConverter = PressureConverter()
        backgroundDrawableHelper = BackgroundDrawableHelper()

        constraintLayoutWeatherInfoActivity = findViewById(R.id.constraintLayout_activity_weather_info)

        launch {
            val weatherResponse =
                ApiFactory.getWeatherById(
                    intent.getIntExtra("id", 0)
                )
            this@WeatherInfoActivity?.runOnUiThread {
                iconUri = ("$BASE_IMAGE_URI${weatherResponse.weather[0].icon}.png".toUri())
                backgroundDrawableName = backgroundDrawableHelper.findPicture(weatherResponse.weather[0].icon)
                constraintLayoutWeatherInfoActivity.setBackgroundResource(
                    getLayoutBackgroundDrawableId(backgroundDrawableName, R.drawable::class.java))
                Glide.with(applicationContext).load(iconUri).into(iv_weather_icon)

                tv_city_temp.text = "${temperatureConverter.degConverter((weatherResponse.main.temp).toInt())} "
                tv_city_name.text = "${weatherResponse.name}, ${weatherResponse.sys.country}"
                tv_description.text = weatherResponse.weather[0].description
                tv_humidity.text = "Humidity: ${weatherResponse.main.humidity} %"
                tv_wind.text = "Wind: ${windConverter.convert(weatherResponse.wind.deg)}, ${weatherResponse.wind.speed} m/s"
                tv_feels_like.text = "Feels like: ${temperatureConverter.degConverter(
                    weatherResponse.main.feelsLike.toInt()
                )}"
                tv_pressure.text = "Pressure: ${pressureConverter.convert(weatherResponse.main.pressure)} m.m."
            }
        }
    }

    private fun getLayoutBackgroundDrawableId(resourceName: String, c: Class<*>): Int {
        try {
            val idField: Field = c.getDeclaredField(resourceName)
            return idField.getInt(idField)
        } catch (e: Exception) {
            throw RuntimeException(
                    "No resource ID found for: $resourceName / $c, $e"
            )
        }
    }

}