package com.example.tasklist.finished_tasklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core_models.domain.FinishedTask
import com.example.core_navigation.NavEvent
import com.example.tasklist.presentation.components.FinishedTaskCard
import com.example.tasklist.presentation.components.TaskCard
import com.example.tasklist.presentation.tasklist.TaskListScreenEvent

class FinishedTaskListScreen()

@Composable
fun FinishedTaskScreen(){
    val viewModel = hiltViewModel<FinishedTaskListViewModel>()
    val finishedTaskListState by viewModel.finishedTaskList.collectAsState()
    FinishedTaskScreenView(finishedTaskListState = finishedTaskListState)
}

@Composable
fun FinishedTaskScreenView(
    finishedTaskListState : List<FinishedTask>
){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp)
        ) {
            items(finishedTaskListState.size){ index->
                FinishedTaskCard(
                    finishedTask = finishedTaskListState[index],
                )
            }
        }
    }
}