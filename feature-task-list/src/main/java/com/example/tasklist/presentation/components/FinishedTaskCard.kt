package com.example.tasklist.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_models.domain.FinishedTask
import com.example.core_models.domain.TaskCategory
import com.example.tasklist.util.toDate
import java.time.Instant

@Composable
fun FinishedTaskCard(
    finishedTask: FinishedTask,
    modifier: Modifier = Modifier,
){
    Card(modifier = modifier
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor =
            if (finishedTask.finishedInTime || finishedTask.finishedAsUnimportant){
                Color.Green
            }else {
                Color.Red
            }
        ),
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = finishedTask.taskName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            if (finishedTask.deadline != null){
                Text(
                    text = "Дедлайн: " + finishedTask.deadline!!.toEpochMilli().toDate(),
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = "Задания завершено: ${finishedTask.finishTimestamp.toEpochMilli().toDate()}",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
            HorizontalDivider(modifier.padding(horizontal = 8.dp), thickness = 1.dp, color = Color.Black)
            Text(text = "Категория: ${finishedTask.taskCategory}", modifier = Modifier.padding(8.dp))
            HorizontalDivider(modifier.padding(horizontal = 8.dp), thickness = 1.dp, color = Color.Black)
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .horizontalScroll(rememberScrollState()),
                text = finishedTask.taskDescription
            )
        }
    }
}

@Preview
@Composable
fun FinishedTaskCardPreview(){
    FinishedTaskCard(
        finishedTask = FinishedTask(
            taskName = "Имя задания",
            taskDescription = "Сделай че-то",
            id = 21,
            deadline = Instant.ofEpochSecond(6543352),
            taskCategory = TaskCategory.NONURGENT,
            finishTimestamp = Instant.ofEpochSecond(6543652),
            finishedInTime = false,
            finishedAsUnimportant = false,
        ),
    )
}


@Preview
@Composable
fun FinishedTaskCardPreview2(){
    FinishedTaskCard(
        finishedTask = FinishedTask(
            taskName = "Имя задания",
            taskDescription = "Сделай че-то",
            id = 21,
            deadline = Instant.ofEpochSecond(6543952),
            taskCategory = TaskCategory.NONURGENT,
            finishTimestamp = Instant.ofEpochSecond(6543652),
            finishedInTime = true,
            finishedAsUnimportant = false,
        ),
    )
}