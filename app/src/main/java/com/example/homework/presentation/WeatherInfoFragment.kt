package com.example.homework.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.homework.R
import com.example.homework.data.api.ApiFactory
import com.example.homework.data.repository.RemoteRepository
import com.example.homework.domain.converters.PressureConverter
import com.example.homework.domain.converters.TemperatureConverter
import com.example.homework.domain.converters.WindConverter
import com.example.homework.domain.facade.CacheProviderFacade
import com.example.homework.domain.helpers.BackgroundDrawableHelper
import com.example.homework.domain.helpers.ConnectionHelper
import kotlinx.android.synthetic.main.fragment_weather_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


const val BASE_IMAGE_URI = "http://openweathermap.org/img/wn/"

class WeatherInfoFragment : Fragment(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    private lateinit var temperatureConverter: TemperatureConverter
    private lateinit var connectionHelper: ConnectionHelper
    private lateinit var windConverter: WindConverter
    private lateinit var pressureConverter: PressureConverter
    private lateinit var backgroundDrawableHelper: BackgroundDrawableHelper
    private lateinit var repository: RemoteRepository
    private lateinit var cacheProviderFacade: CacheProviderFacade

    private lateinit var constraintLayoutWeatherInfoActivity: ConstraintLayout

    private var backgroundDrawableId: Int? = null
    private lateinit var iconUri: Uri
    private var cityId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityId = arguments?.getInt("id")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_info, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        temperatureConverter = TemperatureConverter()
        repository = RemoteRepository()
        windConverter = WindConverter()
        pressureConverter = PressureConverter()
        backgroundDrawableHelper = BackgroundDrawableHelper()
        connectionHelper = ConnectionHelper()


        context?.let {
            cacheProviderFacade = CacheProviderFacade(it)

        }

        constraintLayoutWeatherInfoActivity =
            view?.findViewById(R.id.constraintLayout_activity_weather_info)

        cityId?.let {
            context?.let { context ->
                if (connectionHelper.hasConnect(context)) {
                    launch {
                        val weatherResponse =
                            repository.getWeatherById(it)

                        activity?.runOnUiThread {
                            iconUri =
                                ("$BASE_IMAGE_URI${weatherResponse.weather[0].icon}.png".toUri())

                            backgroundDrawableId =
                                backgroundDrawableHelper.findPicture(weatherResponse.weather[0].icon)
                            backgroundDrawableId?.let {
                                constraintLayoutWeatherInfoActivity.setBackgroundResource(
                                    it
                                )
                            }

                            context?.let { it1 ->
                                Glide.with(it1).load(iconUri).into(iv_weather_icon)
                            }

                            tv_city_temp.text =
                                "${temperatureConverter.convert((weatherResponse.main.temp).toInt())} "
                            tv_city_name.text =
                                "${weatherResponse.name}, ${weatherResponse.sys.country}"
                            tv_description.text = weatherResponse.weather[0].description
                            tv_humidity.text = "Humidity: ${weatherResponse.main.humidity} %"
                            tv_wind.text =
                                "Wind: ${windConverter.convert(weatherResponse.wind.deg)}, ${weatherResponse.wind.speed} m/s"
                            tv_feels_like.text = "Feels like: ${
                                temperatureConverter.convert(
                                    weatherResponse.main.feelsLike.toInt()
                                )
                            }"
                            tv_pressure.text =
                                "Pressure: ${pressureConverter.convert(weatherResponse.main.pressure)} m.m."
                        }
                    }
                } else {
                    launch {
                        val weatherCache = cacheProviderFacade.getFromDBById(it)

                        activity?.runOnUiThread {
                            backgroundDrawableId =
                                backgroundDrawableHelper.findPicture(weatherCache.iconId)
                            backgroundDrawableId?.let {
                                constraintLayoutWeatherInfoActivity.setBackgroundResource(
                                    it
                                )
                            }


                            tv_city_temp.text =
                                "${temperatureConverter.convert((weatherCache.temp).toInt())} "
                            tv_city_name.text = "${weatherCache.name}"
                            tv_description.text = weatherCache.description
                            tv_humidity.text = "Humidity: ${weatherCache.humidity} %"
                            tv_wind.text = "${weatherCache.windSeed} m/s"
                            tv_feels_like.text = "Feels like: ${
                                temperatureConverter.convert(
                                    weatherCache.feelsLike.toInt()
                                )
                            }"
                            tv_pressure.text =
                                "Pressure: ${pressureConverter.convert(weatherCache.pressure)} m.m."
                        }
                    }
                }
            }
        }
    }
}