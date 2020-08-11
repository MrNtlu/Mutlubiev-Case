package com.example.mutlubievcase.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun showToast(context: Context?, message: String) = Toast.makeText(context,message,Toast.LENGTH_SHORT).show()

fun printLog(tag: String = "Test",message:String)= Log.d(tag,message)

fun Date.toFormattedString(): String{
    val format=SimpleDateFormat("dd.MM.yyyy",Locale("tr","TR"))
    return format.format(this)
}

fun Date.getDayFromDate(): String{
    val format=SimpleDateFormat("EEEE",Locale("tr","TR"))
    return format.format(this)
}
