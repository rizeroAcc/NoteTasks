package com.example.tasklist.presentation.tasklist

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core_models.domain.Task
import com.example.core_navigation.NavEvent
import com.example.tasklist.presentation.components.TaskCard
import com.example.tasklist.util.toDate

object TaskListScreen


 @Composable
 fun TaskListScreen(
     onNavigateTo : (navigationEvent: NavEvent)->Unit
 ){
     val viewModel = hiltViewModel<TaskListScreenViewModel>()
     val taskList by viewModel.taskList.collectAsState()

     TaskListScreenView(
         onNavigateTo = onNavigateTo,
         taskListState = taskList,
         onEvent = { event ->
             viewModel.handleEvent(event)
         }
     )
 }

@Composable
fun TaskListScreenView(
    onNavigateTo: (NavEvent) -> Unit,
    taskListState : List<Task>,
    onEvent : (event : TaskListScreenEvent)->Unit = {},
){
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp)
        ) {
            items(taskListState.size, key = {taskListState[it].id}){ index->
                TaskCard(
                    task = taskListState[index],
                    onCardClick = {
                        onNavigateTo(NavEvent.ShowEditTaskModal(taskID = taskListState[index].id, onTaskUpdated = {
                            onEvent(TaskListScreenEvent.TaskListUpdated)
                        }))
                    },
                    modifier = Modifier.animateItem()
                )
            }
        }
        Button(
            onClick = {
                onNavigateTo(NavEvent.ShowCreateTaskModal(
                    onTaskCreated = {
                        onEvent(TaskListScreenEvent.TaskListUpdated)
                    }
                ))
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