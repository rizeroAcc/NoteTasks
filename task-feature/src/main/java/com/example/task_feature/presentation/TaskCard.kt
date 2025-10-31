package com.example.task_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ModalTaskCardData(
    val taskTitle: String,
    val taskDescription: String,
    val deadline : String? = null,
    val taskCategory : String
)
@Composable
fun ModalTaskCard(
    taskTitle : String,
    taskDescription: String,
    deadline : String? = null,
    taskCategory : String
){
    var taskDescriptionState by remember { mutableStateOf(taskDescription) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 12.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = taskTitle,
                    fontSize = 20.sp
                )
            }
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(8.dp)
                    ){
                Column(modifier = Modifier.fillMaxWidth()){
                    if (deadline != null){
                        Text(
                            modifier = Modifier.padding(top = 12.dp, start = 4.dp),
                            text = "Дедлайн: $deadline"
                        )
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 12.dp, start = 4.dp),
                        text = "Категория: $taskCategory"
                    )
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = taskDescriptionState,
                        onValueChange = { newValue->
                            taskDescriptionState = newValue
                        },
                        label = {
                            Text("Описание задания")
                        },
                        placeholder = {
                            Text("Описание задания")
                        }
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = {}) { Text("Сохранить") }
                Button(onClick = {}) { Text("Завершить")}
            }
        }
    }
}

@Composable
@Preview
fun ModalTaskCardPreview(){
    ModalTaskCard(
        taskTitle = "Название задания",
        taskDescription = "Сделай чето там",
        deadline = "26.04/21:30",
        taskCategory = "Срочное"
    )
}