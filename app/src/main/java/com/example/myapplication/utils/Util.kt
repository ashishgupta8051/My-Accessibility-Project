package com.example.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

fun checkInternetConnection(context: Context): Boolean{
    if (context != null) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val netInfo = connectivityManager.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected) {
                return true
            }
        }
    }
    return false
}


fun showLog(value: String){
    Log.e("DATA",value)
}

fun showContext(context: Context, value: String){
    Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
}
