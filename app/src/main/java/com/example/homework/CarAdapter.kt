package com.example.homework;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.homework.models.Car;

class CarAdapter(private var list: List<Car>,
                 private val itemClick: (id:Int) -> Unit):RecyclerView.Adapter<CarHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder =
        CarHolder.getInstance(parent,itemClick)

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: CarHolder, position: Int) = holder.bind(list[position])

        }