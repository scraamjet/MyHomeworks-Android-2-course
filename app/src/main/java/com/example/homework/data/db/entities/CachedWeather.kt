package com.example.homework.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache")
data class CachedWeather(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "wind_speed")
    var windSeed: Double,
    @ColumnInfo(name = "temp")
    var temp: Double,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "icon_id")
    var iconId: String,
    @ColumnInfo(name = "feels_like")
    var feelsLike: Double,
    @ColumnInfo(name = "humidity")
    var humidity: Int,
    @ColumnInfo(name = "pressure")
    var pressure: Int,
)