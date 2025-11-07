package com.example.tasklist.presentation.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_models.domain.Task
import com.example.core_models.domain.TaskCategory
import com.example.tasklist.util.toDate
import java.time.Instant

@Composable
fun TaskCard(
    task: Task,
    onCardClick:()->Unit,
    modifier: Modifier = Modifier,
){
    Card(modifier = modifier
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        onClick = onCardClick
        ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = task.taskName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            if (task.deadline != null){
                Text(
                    text = "Дедлайн: " + task.deadline!!.toEpochMilli().toDate(),
                    modifier = Modifier.padding(8.dp)
                )
                HorizontalDivider(modifier.padding(horizontal = 8.dp), thickness = 1.dp, color = Color.Black)
            }
            Text(text = "Категория:${task.category}", modifier = Modifier.padding(8.dp))
            HorizontalDivider(modifier.padding(horizontal = 8.dp), thickness = 1.dp, color = Color.Black)
            Text(
                modifier = Modifier.
                    padding(8.dp).
                    horizontalScroll(rememberScrollState()),
                text = task.taskDescription
            )
        }
    }
}

@Preview
@Composable
fun TaskCardPreview(){
    TaskCard(
        task = Task(
            taskName = "Имя задания",
            taskDescription = "Сделай че-то",
            category = TaskCategory.LONGTIME,
            deadline = Instant.ofEpochSecond(263424),
        ),
        onCardClick = {}
    )
}