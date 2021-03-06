package com.example.homework.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.dto.CityDTO

class CityAdapter (
    private var list: List<CityDTO>,
    private val itemClick: (id:Int)->Unit,

    ): RecyclerView.Adapter<CityHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
    CityHolder.getInstance(parent,itemClick)

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateDataList(newList: List<CityDTO>){
        list = newList
        notifyDataSetChanged()
    }
}