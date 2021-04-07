package com.example.homework.domain.helpers

import com.example.homework.R

class BackgroundDrawableHelper {

    fun findPicture(icon_id:String):Int{
        return when(icon_id){
            "01d","01n","02d","02n","03d","03n","04d","04n"-> R.drawable.background_sky
            "09d","09n","10d","10n","11d","11n","50d","50n"-> R.drawable.background_rain
            "13d","13n"-> R.drawable.background_snow
            else-> R.drawable.background_sky
        }
    }

}