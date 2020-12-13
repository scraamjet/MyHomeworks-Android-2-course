package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    lateinit var personalInfo:TextView
    lateinit var editNameSurnameFunctionalButton:Button
    lateinit var editPersonalInfo:EditText
    lateinit var showInfo:Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        personalInfo = findViewById(R.id.name_surname)
        editNameSurnameFunctionalButton = findViewById(R.id.edit_name_surname_functional_button)
        //Невидимый до нажатия кнопки "Изменить имя и фамилию" EditText
        editPersonalInfo = findViewById(R.id.edit_name_surname)
        showInfo = findViewById(R.id.see_information_functional_button)

        var name = intent.getStringExtra("name")
        var surname = intent.getStringExtra("surname")
        personalInfo.setText("$surname $name")

        editNameSurnameFunctionalButton.setOnClickListener {
            editPersonalInfo.text.clear()
            //При нажатии на кнопку "Изменить имя и фамилию" EditText становится видимым,
            //Надпись Имя_Фамилию пропадает до нажатия на "OK"
            personalInfo.visibility = View.INVISIBLE
            editPersonalInfo.visibility = View.VISIBLE
            var userInput = editPersonalInfo.text

            editPersonalInfo.setOnKeyListener { v, keyCode, event ->
                //Нельзя оставлять TextView пустым
                //Проверка на нажатие кнопки "OK"
                //После нажатия кнопки "OK" клавиатура пропадает
                if (userInput.isNotEmpty() && keyCode == KeyEvent.KEYCODE_ENTER&&event.action == KeyEvent.ACTION_UP) {
                    personalInfo.text = userInput.toString()
                    editPersonalInfo.visibility = View.INVISIBLE
                    personalInfo.visibility = View.VISIBLE
                }
                false
            }
        }

        showInfo.setOnClickListener {
            //Перемещение нужных для ShowInformationActivity данных из intent в другой
            var intent2 = Intent(this,ShowInformationActivity::class.java)
            intent2.putExtra("work",intent.getStringExtra("work"))
            intent2.putExtra("city",intent.getStringExtra("city"))
            intent2.putExtra("email",intent.getStringExtra("email"))
            startActivity(intent2)
        }
    }
}
