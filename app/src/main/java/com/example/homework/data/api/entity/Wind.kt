package com.example.homework.data.api.entity

import com.google.gson.annotations.SerializedName

data class Wind(@SerializedName("deg")
                var deg: Double,
                @SerializedName("speed")
                var speed: Double)
