package com.example.homework.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.models.City

class CityAdapter (
    private val list: List<City>
    ): RecyclerView.Adapter<CityHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder = CityHolder.create(parent)

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}