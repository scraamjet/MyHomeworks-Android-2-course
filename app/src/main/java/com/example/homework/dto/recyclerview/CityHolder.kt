package com.example.homework.dto.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.dto.CityDTO
import com.example.homework.helpers.TemperatureConverter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.*

class CityHolder (
    override val containerView: View,
    private val itemClick: (id:Int)->Unit,
        ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer{

    private var city: CityDTO? = null
    private val temperatureConverter = TemperatureConverter()

    @SuppressLint("SetTextI18n")
    fun bind(city: CityDTO){
                this.city = city
                with(city) {
                    city_name.text = name
                    temp_text.text = temperatureConverter.degConverter(temp)
                    temp_text.setTextColor(temperatureConverter.findTempColor(temp.toInt()))
                }
                containerView.setOnClickListener{
                    itemClick(city.id)
                }
            }

    companion object {
        fun getInstance(parent: ViewGroup,itemClick: (id:Int)->Unit) = CityHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_city, parent, false),itemClick)
    }
}