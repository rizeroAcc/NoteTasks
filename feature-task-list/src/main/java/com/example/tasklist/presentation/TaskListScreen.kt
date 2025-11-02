package com.example.tasklist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tasklist.presentation.components.TaskCard

object TaskListScreen

@Deprecated("For test only")
data class TaskDeatils(
    val taskTitle: String,
    val taskDescription: String,
    val deadline : String? = null,
    val taskCategory : String
)

@Composable
fun TaskListScreen(onCreateTaskClick : () -> Unit, onCardClick: (taskDetails : TaskDeatils) -> Unit){
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp)
        ) {
            items(30){
                TaskCard("Имя задания", "Сделай че-то много - много - много - много - много - текста", onCardClick = {
                    onCardClick(
                        TaskDeatils("Имя задания", taskDescription = "Сделай че-то много - много - много - много - много - текста", taskCategory = "Срочно", deadline = "26.05 23:30")
                    )
                })
            }
        }
        Button(
            onClick = {
                onCreateTaskClick()
            },
            modifier = Modifier
                .padding(end = 20.dp, bottom = 20.dp)
                .clip(CircleShape)
                .size(64.dp)
                .align(Alignment.BottomEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}