package com.example.tasklist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

object FinishedTaskListScreen

@Composable
fun FinishedTaskScreen(onBackClick : () -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(modifier = Modifier.fillMaxSize()){
            Text("Finished task list screen")
            Button(onClick = onBackClick) {
                Text("Go back")
            }
        }
    }
}