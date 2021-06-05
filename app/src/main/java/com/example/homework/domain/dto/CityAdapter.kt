package com.example.homework.domain.dto

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CityAdapter (
        private var list: List<CityItem>,
        private val itemClick: (id:Int)->Unit,

        ): RecyclerView.Adapter<CityHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
            CityHolder.getInstance(parent, itemClick)

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateDataList(newList: List<CityItem>){
        list = newList
        notifyDataSetChanged()
    }
}