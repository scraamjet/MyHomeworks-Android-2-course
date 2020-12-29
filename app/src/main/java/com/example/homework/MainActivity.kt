package com.example.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.homework.Fragments.*

class MainActivity : AppCompatActivity() {
    private var imageView: ImageView? = null

    private lateinit var icon_1: ImageView
    private lateinit var icon_2: ImageView
    private lateinit var icon_3: ImageView
    private lateinit var icon_4: ImageView
    private lateinit var icon_5: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        icon_1 = findViewById(R.id.icon_1)
        icon_2 = findViewById(R.id.icon_2)
        icon_3 = findViewById(R.id.icon_3)
        icon_4 = findViewById(R.id.icon_4)
        icon_5 = findViewById(R.id.icon_5)

        fragment_render(icon_1, Fragment_1())
        fragment_render(icon_2, Fragment_2())
        fragment_render(icon_3, Fragment_3())
        fragment_render(icon_4, Fragment_4())
        fragment_render(icon_5, Fragment_5())

    }

    private fun fragment_render(icon: ImageView, fragment: Fragment) {
        icon.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment, fragment)
            transaction.commit()
            imageView?.setColorFilter(0)
            imageView = icon
            imageView?.setColorFilter(R.color.colorAccent)
        }
    }
}