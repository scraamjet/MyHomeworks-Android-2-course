package com.example.homework.domain.helpers

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat


const val GOOD_REQUEST_CODE = 1
class PermissionHelper {

    fun check(context: Context) {
        if (ActivityCompat.checkSelfPermission(context as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION), GOOD_REQUEST_CODE)
        }
    }
    fun handle(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) :Boolean {
        return if (requestCode == GOOD_REQUEST_CODE && grantResults.size == 2 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.i("Permission", "There are permissions")
            true
        } else {
            Log.i("Permission", "There are no permissions")
            false
        }
    }
}