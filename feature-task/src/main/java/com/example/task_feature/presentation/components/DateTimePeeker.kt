package com.example.task_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task_feature.util.toMills
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePeeker(
    selectedTimeMills : Instant? = null,
    onConfirm: (timestamp : Long) -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = selectedTimeMills?.atZone(ZoneId.systemDefault())?.hour ?: 12,
        initialMinute = selectedTimeMills?.atZone(ZoneId.systemDefault())?.minute ?: 0,
        is24Hour = true,
    )

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedTimeMills?.toEpochMilli()?: Instant.now().toEpochMilli()
    )
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .padding(24.dp)
            ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
            TimeInput(
                state = timePickerState,
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = { onConfirm(
                    datePickerState.selectedDateMillis!! + timePickerState.toMills()
                ) },
                enabled = datePickerState.selectedDateMillis != null) {
                Text("Выбрать")
            }
        }
    }
}

@Preview
@Composable
fun DateTimePeekerPreview(){
    DateTimePeeker(onConfirm = {})
}

