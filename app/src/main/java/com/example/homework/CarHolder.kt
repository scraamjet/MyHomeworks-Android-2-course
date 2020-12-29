package com.example.homework

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.example.homework.models.Car

import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.car.*

class CarHolder(override val containerView: View,private val itemClick: (id: Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    private var car: Car? = null

    companion object {
        fun getInstance(parent: ViewGroup, itemClick: (id: Int) -> Unit): CarHolder =
            CarHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.car, parent, false),itemClick)
    }

    @SuppressLint("SetTextI18n")
    fun bind(car: Car) {
        this.car = car
        containerView.setOnClickListener {
            itemClick(car.id)
            }
            with(car) {
                car_icon.setImageResource(car.img)
                brand_info.text = car.brand
                model_info.text = car.model
                horsepower_info.text = "Мощность: "+car.horsepower.toString()+" л.с."
                engine_capacity_info.text = "Объем двигателя: "+car.engine_capacity.toString()
        }

    }
}