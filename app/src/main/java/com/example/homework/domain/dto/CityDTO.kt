package com.example.homework.domain.dto

import androidx.room.ColumnInfo

data class CityDTO (
        var id:Int,
        var name:String,
        var temp:Double,
        var windSeed: Double,
        var description: String,
        var iconId: String,
        var feelsLike: Double,
        var humidity: Int,
        var pressure: Int,
        )