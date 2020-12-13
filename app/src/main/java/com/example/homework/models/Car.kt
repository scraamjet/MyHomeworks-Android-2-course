package com.example.homework.models

open class Car(var brand:String, var model:String, var year: Int, var max_speed:Int) :CarInterface {

    public open override fun info(){
        println("Car info:")
        println(toString())
    }
    public override fun toString(): String {
        return "brand: $brand \n model: $model\n year: $year \n max speed: $max_speed km/h"
    }
}