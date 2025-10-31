package com.example.tasklist.presentation.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasklist.presentation.TaskDeatils

@Composable
fun TaskCard(taskName : String, shortDescription : String, onCardClick:()->Unit){
    Card(modifier = Modifier
        .drawBehind({Color.Red})
        .fillMaxWidth()
        .heightIn(50.dp,100.dp),
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
                Text(taskName)
            }
            Text(
                modifier = Modifier.
                    padding(start = 8.dp).
                    scrollable(rememberScrollableState { 0.0f }, orientation = Orientation.Horizontal),
                text = shortDescription
            )
        }
    }
}

@Preview
@Composable
fun TaskCardPreview(){
    TaskCard(taskName = "Имя задания", shortDescription = "Сделай че-то", onCardClick = {})
}