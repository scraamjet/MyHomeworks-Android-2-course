package com.example.homework.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.homework.R
import com.example.homework.domain.helpers.PermissionHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*

class MainActivity : AppCompatActivity() {
    private lateinit var permissionHelper: PermissionHelper
    private lateinit var searchFragment: SearchFragment
    private lateinit var weatherInfoFragment: WeatherInfoFragment
    private val onClick: (Int) -> Unit = {
        weatherInfoFragment = WeatherInfoFragment()
        weatherInfoFragment.arguments = Bundle().apply { putInt("id",it) }
        swapFragment(weatherInfoFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionHelper = PermissionHelper()
        searchFragment = SearchFragment(onClick)

        swapFragment(searchFragment)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (!permissionHelper.handle(requestCode, permissions, grantResults)) {
            permissionHelper.check(this)
            Snackbar.make(fl_main,
                "no permissions dude",
                Snackbar.LENGTH_SHORT).show()
        } else {
            swapFragment(searchFragment)
            Snackbar.make(fl_main,
                "nice dude",
                Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun swapFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fl_main,fragment).
        addToBackStack("app_stack").commit()
    }

}