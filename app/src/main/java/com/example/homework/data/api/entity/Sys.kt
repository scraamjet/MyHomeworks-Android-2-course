package com.example.homework.data.api.entity

import com.google.gson.annotations.SerializedName

data class Sys (    @SerializedName("country")
                   var country: String,
                   @SerializedName("id")
                   var id: Int,
                   @SerializedName("sunrise")
                   var sunrise: Int,
                   @SerializedName("sunset")
                   var sunset: Int,
                   @SerializedName("type")
                   var type: Int)