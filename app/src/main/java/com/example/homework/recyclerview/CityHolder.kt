package com.example.homework.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.models.City
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.*

class CityHolder (
    override val containerView: View
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer{
            fun bind(city: City){
                city_name.text = city.name
                temp_text.text = city.temp.toString()
            }
    companion object {

        fun create(parent: ViewGroup) = CityHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        )
    }
}