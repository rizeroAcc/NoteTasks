package com.example.task_feature.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit


@OptIn(ExperimentalMaterial3Api::class)
fun TimePickerState.toMills() : Long {
    return (hour * 60 * 60 * 1000 + minute*60 * 1000).toLong()
}

fun Long.toDate() : String{
    val date = Date(this)
    return SimpleDateFormat("dd.MM/HH:mm").format(date)
}