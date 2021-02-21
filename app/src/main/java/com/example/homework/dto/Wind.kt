package com.example.homework.dto

import com.google.gson.annotations.SerializedName

data class Wind(@SerializedName("deg")
                var deg: Int,
                @SerializedName("speed")
                var speed: Double)
