package com.example.homework.domain.facade

import android.content.Context
import com.example.homework.data.api.entity.WeatherResponse
import com.example.homework.data.db.Converter
import com.example.homework.data.db.WeatherDatabase
import com.example.homework.data.db.entities.CachedWeather
import com.example.homework.data.repository.LocalRepository
import com.example.homework.data.repository.RemoteRepository
import com.example.homework.domain.dto.CityDTO
import com.example.homework.domain.dto.CityItem

class CacheProviderFacade(context: Context) {

    private val converter = Converter()
    private val db = WeatherDatabase.getInstance(context)
    private val weatherDao = db.dao()

    suspend fun saveToDB(response: List<WeatherResponse>){
        weatherDao.insert(converter.mapFromDTO(converter.mapFromResponse(response)))
    }

    suspend fun getDTOFromDB():List<CityDTO>{
        return converter.mapToDTO(weatherDao.getData())
    }
    suspend fun getItemFromDB():List<CityItem>{
        return converter.mapToCityItemDTO(weatherDao.getData())
    }

    suspend fun updateDB(response: List<WeatherResponse>){
        weatherDao.clearData()
        weatherDao.insert(converter.mapFromDTO(converter.mapFromResponse(response)))
    }


    suspend fun getFromDBById(id:Int):CityDTO{
        return converter.mapToDTO(weatherDao.getDataById(id))
    }

}