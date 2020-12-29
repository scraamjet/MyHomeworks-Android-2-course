package com.example.homework

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var changing_color_text:TextView

    private lateinit var yes_button:Button
    private lateinit var no_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        changing_color_text = findViewById(R.id.changing_color_text)
        yes_button = findViewById(R.id.yes_button)
        no_button = findViewById(R.id.no_button)

        var recievedIntent = intent
        var selected_color = recievedIntent.getIntExtra("color",0)
        changing_color_text.setTextColor(selected_color)

        yes_button.setOnClickListener {
            var resultIntent = Intent()
            resultIntent.putExtra("color",selected_color)

            setResult(RESULT_OK,resultIntent)
            finish()
        }

        no_button.setOnClickListener {
            var resultIntent = Intent()

            setResult(RESULT_CANCELED,resultIntent)
            finish()
        }
    }
}