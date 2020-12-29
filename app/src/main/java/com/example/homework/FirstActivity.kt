package com.example.homework

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class FirstActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val REQUEST_COLOR_CODE = 887
    }

    private lateinit var main_text:TextView

    private lateinit var red_button: Button
    private lateinit var yellow_button: Button
    private lateinit var blue_button:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)
        main_text = findViewById(R.id.main_text)
        red_button = findViewById(R.id.red_button)
        yellow_button = findViewById(R.id.yellow_button)
        blue_button = findViewById(R.id.blue_button)

        red_button.setOnClickListener(this)
        yellow_button.setOnClickListener(this)
        blue_button.setOnClickListener(this)
    }

      override fun onClick(view: View){
         val sendIntent = Intent()
         sendIntent.action = Intent.ACTION_SEND
         sendIntent.type = "text/plain"

            when(view.id){
                R.id.red_button -> sendIntent.putExtra("color",Color.RED)
                R.id.yellow_button -> sendIntent.putExtra("color",Color.YELLOW)
                R.id.blue_button -> sendIntent.putExtra("color",Color.BLUE)
            }
             startActivityForResult(sendIntent, REQUEST_COLOR_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_COLOR_CODE){
            if(resultCode == RESULT_OK){
                var selected_color = data?.getIntExtra("color",0)
                if (selected_color != null) {
                    main_text.setTextColor(selected_color)
                }
                Toast.makeText(applicationContext,"Color of your text was successfully changed!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext,"Color of your text was not changed!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}