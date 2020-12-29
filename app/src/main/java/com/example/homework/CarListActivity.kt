package com.example.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework.models.Car
import com.example.homework.repositories.CarRepository
import kotlinx.android.synthetic.main.car_list.*

class CarListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.car_list)

        val carRepository = CarRepository()

        val carAdapter = CarAdapter(carRepository.getAll()){
            showCar(it)
        }
        car_list.adapter = carAdapter
    }

    private fun showCar(id:Int){
        val sendCarIntent = Intent(this,CarActivity::class.java)
        sendCarIntent.putExtra("id",id)
        startActivity(sendCarIntent)
    }

}