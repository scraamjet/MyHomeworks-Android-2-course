package com.example.homework.services

class PressureConverter {
    fun convert(press:Int):Int{
        return (press/1.33).toInt()
    }
}