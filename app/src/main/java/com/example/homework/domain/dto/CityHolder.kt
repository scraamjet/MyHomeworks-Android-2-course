package com.example.homework.domain.dto

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.domain.converters.TemperatureConverter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.*

class CityHolder (
    override val containerView: View,
    private val itemClick: (id:Int)->Unit,
        ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer{

    private var city: CityItem? = null
    private val temperatureConverter = TemperatureConverter()

    @SuppressLint("SetTextI18n")
    fun bind(city: CityItem){
                this.city = city
                with(city) {
                    city_name.text = name
                    temp_text.text = temperatureConverter.convert(temp.toInt())
                    temp_text.setTextColor(temperatureConverter.findTempColor(temp.toInt()))
                }
                itemView.setOnClickListener{
                    itemClick(city.id)
                }
            }

    companion object {
        fun getInstance(parent: ViewGroup,itemClick: (id:Int)->Unit) = CityHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_city, parent, false),itemClick)
    }
}