package com.example.homework.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework.data.db.entities.CachedWeather


@Dao
interface LocalRepository {

    @Insert
    fun insert(cachedWeatherList:List<CachedWeather>)

    @Query("SELECT * FROM cache")
    fun getData(): List<CachedWeather>
    @Query("DELETE FROM cache")
    fun clearData()
    @Query("SELECT * FROM cache WHERE name = :name")
    fun getDataByName(name: String): CachedWeather
    @Query("SELECT * FROM cache WHERE id = :id")
    fun getDataById(id: Int): CachedWeather

}