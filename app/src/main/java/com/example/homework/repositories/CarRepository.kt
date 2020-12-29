package com.example.homework.repositories

import com.example.homework.R
import com.example.homework.models.Car

class CarRepository {
    private val carList = ArrayList<Car>()
        init {
            with(carList){
                carList.add(Car(0,"BMW","520d", R.drawable.bmw_520d_icon,190,2.0,"Задний",235))
                carList.add(Car(1,"Mitsubishi","Lancer X",R.drawable.mitsubishi_lancer_x,150,2.0,"Передний",200))
                carList.add(Car(2,"Audi","Q7",R.drawable.audi_q7_icon,249,3.0,"Полный",225))
                carList.add(Car(3,"Toyota","Land Cruiser",R.drawable.toyota_land_cruiser_200,249,4.5,"Полный",195))
                carList.add(Car(4,"Toyota","Camry",R.drawable.toyota_camry_xv_50_icon,249,3.5,"Передний",227))
            }
        }
    fun findCarByID(id:Int):Car?{
        if(id>=carList.size)return null
        return carList[id]
    }
    fun getAll():ArrayList<Car>{
        return carList;
    }
}