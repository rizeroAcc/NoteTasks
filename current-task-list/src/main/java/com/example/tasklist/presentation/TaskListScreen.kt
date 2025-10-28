package com.example.tasklist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.tasklist.presentation.components.TaskCard
import java.nio.file.WatchEvent

object TaskListScreen

@Composable
fun TaskListScreen(onBtnClick : () -> Unit){
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp)
        ) {
            items(30){
                TaskCard("Имя задания", "Сделай че-то много - много - много - много - много - текста")
            }
        }
        Button(
            onClick = {},
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