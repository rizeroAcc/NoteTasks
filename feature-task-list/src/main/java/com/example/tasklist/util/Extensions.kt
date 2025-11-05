package com.example.tasklist.util

import java.text.SimpleDateFormat
import java.util.Date

fun Long.toDate() : String{
    val date = Date(this)
    return SimpleDateFormat("dd.MM/HH:mm").format(date)
}