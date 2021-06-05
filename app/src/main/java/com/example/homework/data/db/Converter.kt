package com.example.homework.data.db

import androidx.room.TypeConverter
import com.example.homework.data.api.entity.WeatherList
import com.example.homework.data.api.entity.WeatherResponse
import com.example.homework.data.db.entities.CachedWeather
import com.example.homework.domain.dto.CityDTO
import com.example.homework.domain.dto.CityItem

class Converter {
    @TypeConverter
    fun mapFromDTO(cityDTOList: List<CityDTO>): List<CachedWeather> {
        var cacheList = ArrayList<CachedWeather>()
        cityDTOList.forEach() {
            with(it) {
                cacheList.add(CachedWeather(
                        id, name,
                        windSeed,
                        temp,
                        description,
                        iconId,feelsLike,
                        humidity, pressure
                    )
                )
            }
        }
        return cacheList
    }
    @TypeConverter
    fun mapToDTO(cacheList: List <CachedWeather>): List<CityDTO> {
        var cityDTOList = ArrayList<CityDTO>()
        cacheList.forEach() {
            with(it) {
                cityDTOList.add(CityDTO(
                        id, name,
                        windSeed,
                        temp,
                        description,
                        iconId,feelsLike,
                        humidity, pressure
                )
                )
            }
        }
        return cityDTOList
    }

    @TypeConverter
    fun mapToDTO(cache: CachedWeather): CityDTO {
            with(cache) {
                return CityDTO(
                        id, name,
                        windSeed,
                        temp,
                        description,
                        iconId,feelsLike,
                        humidity, pressure
                )
            }
        }

    @TypeConverter
    fun mapToCityItemDTO(cacheList: List <CachedWeather>): List<CityItem> {
        var itemList = ArrayList<CityItem>()

        cacheList.forEach() {
            with(it) {
                itemList.add(CityItem(
                        id, name,
                        temp
                )
                )
            }
        }
        return itemList
    }
    @TypeConverter
    fun mapFromResponse(weatherList: List<WeatherResponse>): List<CityDTO> {
        var cityDTOList = ArrayList<CityDTO>()
        weatherList.forEach() {
            with(it) {
                cityDTOList.add(CityDTO(
                        id, name,
                        main.temp,
                        wind.speed,
                        weather[0].description,
                        weather[0].icon,main.feelsLike,
                        main.humidity, main.pressure
                )
                )
            }
        }
        return cityDTOList
    }

}