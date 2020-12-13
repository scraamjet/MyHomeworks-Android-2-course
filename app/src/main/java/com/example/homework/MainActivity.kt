package com.example.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework.models.Car
import com.example.homework.models.PassengerCar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val car1 = PassengerCar("BMW","520d",2020,235,"Diesel",190.0,2.0);
        val car2 = PassengerCar("Hyundai","Creta",2017,179,"Gas",149.6,2.0)
        car1.startEngine()
        car1.info()
        car2.startEngine()
        car2.info()
        car1.specificationsInfo()
    }
}