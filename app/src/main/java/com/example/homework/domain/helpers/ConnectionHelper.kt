package com.example.homework.domain.helpers

import android.content.Context
import android.net.ConnectivityManager


class ConnectionHelper {
    fun hasConnect(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}