package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    lateinit var name_input:EditText
    lateinit var surname_input:EditText
    lateinit var work_info_input:EditText
    lateinit var city_info_input:EditText
    lateinit var email_info_input:EditText
    lateinit var registration_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //
        name_input = findViewById(R.id.name_input)
        surname_input = findViewById(R.id.surname_input)
        work_info_input = findViewById(R.id.work_place_input)
        city_info_input = findViewById(R.id.city_input)
        email_info_input = findViewById(R.id.email_input)
        registration_button = findViewById(R.id.sign_up_button)

        registration_button.setOnClickListener {
            val intent = Intent(this,ProfileActivity::class.java)
            intent.putExtra("name",name_input.text.toString())
            intent.putExtra("surname",surname_input.text.toString())
            intent.putExtra("work",work_info_input.text?.toString()?:"\t\tНе указано")
            intent.putExtra("city",city_info_input.text?.toString()?:"\t\tНе указано")
            intent.putExtra("email",email_info_input.text.toString())
            if (validation()){
                startActivity(intent)
            }
        }
    }
    //Проверка на пустое поле
    //имя, фамилия, email - пустыми быть не могут
    fun validation():Boolean{
        if(name_input.text.isEmpty())  {
            name_input.error = "Поле не может быть пустым!"
            return false
        }
        if(surname_input.text.isEmpty()) {
            surname_input.error = "Поле не может быть пустым!"
            return false
        }
        if(email_info_input.text.isEmpty()) {
            email_info_input.error = "Поле не может быть пустым!"
            return false
        }
        return true
    }
}
