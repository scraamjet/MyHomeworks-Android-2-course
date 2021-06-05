package com.example.homework.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homework.data.db.entities.CachedWeather
import com.example.homework.data.repository.LocalRepository

const val DATABASE_NAME = "com.homework.weather.db"

@Database(entities = [CachedWeather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun dao(): LocalRepository
    companion object {
        private lateinit var sInstance: WeatherDatabase
        @Synchronized
        fun getInstance(context: Context): WeatherDatabase {
            if (!Companion::sInstance.isInitialized) {
                sInstance = Room.databaseBuilder(context, WeatherDatabase::class.java,
                        DATABASE_NAME
                ).
                build()
            }
            return sInstance
        }
    }
}