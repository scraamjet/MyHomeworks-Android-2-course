package com.example.homework.domain.converters

import com.example.homework.R

class TemperatureConverter {

        fun findTempColor(temp: Int): Int {
            if (temp <= -30) {
                return R.color.very_cold
            }
            else
                if (temp <= -10 && temp > -30) {
                    return R.color.cold
                }
                else
                    if (temp > -10 && temp <= 10) {
                        return R.color.normal
                    }
                    else
                        if (temp > 10 && temp < 30) {
                            return R.color.warm
                        }
                        else
                            if (temp >= 30) {
                                return R.color.hot
                            }
            return 0
    }

    fun convert(temp:Int):String{
        return if (temp > 0)
            "+${temp}°C"
        else
            "$temp°C"
    }

}