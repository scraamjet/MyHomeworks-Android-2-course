package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ShowInformationActivity : AppCompatActivity() {
    lateinit var back_button:Button
    lateinit var work_info:Button
    lateinit var city_info:Button
    lateinit var email_info:Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_information)

        back_button = findViewById(R.id.back_button)
        work_info = findViewById(R.id.work_info_button)
        city_info = findViewById(R.id.city_info_button)
        email_info = findViewById(R.id.email_button)

        val work: String? = intent.getStringExtra("work")
        val city:String?= intent.getStringExtra("city")
        //email не может быть пустым
        val email: String? = intent.getStringExtra("email")

        if (work != null) {
            if(work.isEmpty()){
                work_info.text = "\t\tНе указано"
            }
            else{
                work_info.text = "\t\t$work"
            }
        }

        if (city != null) {
            if(city.isEmpty()){
                city_info.text = "\t\tНе указано"
            }
            else{
                city_info.text = "\t\t$city"
            }
        }

        email_info.text = "\t\t$email"

        back_button.setOnClickListener {
            onBackPressed()
        }
    }
}