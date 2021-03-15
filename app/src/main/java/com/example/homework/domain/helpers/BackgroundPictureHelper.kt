package com.example.homework.domain.helpers

class BackgroundDrawableHelper {

    fun findPicture(icon_id:String):String{
        return when(icon_id){
            "01d","01n","02d","02n","03d","03n","04d","04n"-> "background_sky"
            "09d","09n","10d","10n","11d","11n","50d","50n"-> "background_rain"
            "13d","13n"-> "background_snow"
            else-> "background_sky"
        }
    }

}