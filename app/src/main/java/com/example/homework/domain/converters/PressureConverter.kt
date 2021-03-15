package com.example.homework.domain.converters

class PressureConverter {

    fun convert(press:Int):Int{
        return (press/1.33).toInt()
    }

}