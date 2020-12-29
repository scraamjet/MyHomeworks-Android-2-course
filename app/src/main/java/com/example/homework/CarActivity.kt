package com.example.homework

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework.repositories.CarRepository
import kotlinx.android.synthetic.main.activity_car.*
import kotlinx.android.synthetic.main.car.*
import kotlinx.android.synthetic.main.car.car_icon

class CarActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        val car_id = intent.getIntExtra("id",0)
        val carRepository = CarRepository()
        val car = carRepository.findCarByID(car_id)
        car?.let{
            icon.setImageResource(car.img)
            brand.setText("Марка: "+car.brand)
            model.setText("Модель: "+car.model)
            horsepower.setText("Мощность: "+car.horsepower.toString()+ " л.с.")
            engine_capacity.setText("Объем двигателя: "+ car.engine_capacity.toString() + " л.")
            type_of_drive.setText("Привод: "+car.type_of_drive)
            max_speed.setText("Макс.скорость: "+car.max_speed.toString()+" км/ч")

        }
    }
}