package com.example.tasklist.finished_tasklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core_models.domain.FinishedTask
import com.example.tasklist.presentation.components.FinishedTaskCard

class FinishedTaskListScreen()

@Composable
fun FinishedTaskScreen(){
    val viewModel = hiltViewModel<FinishedTaskListViewModel>()
    val finishedTaskListState by viewModel.finishedTaskList.collectAsState()
    FinishedTaskScreenView(
        onEvent = { event->
            viewModel.handleEvent(event)
        },
        finishedTaskListState = finishedTaskListState
    )
}

@Composable
fun FinishedTaskScreenView(
    onEvent : (event : FinishedTaskListEvent) -> Unit,
    finishedTaskListState : List<FinishedTask>
){
    val menuCardIndex = remember { mutableStateOf<Int?>(null) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(24.dp)
        ) {
            items(finishedTaskListState.size){ index->
                Box{
                    FinishedTaskCard(
                        finishedTask = finishedTaskListState[index],
                        modifier = Modifier.clickable(
                            onClick = {
                                menuCardIndex.value = index
                            }
                        )
                    )
                    DropdownMenu(
                        expanded = menuCardIndex.value != null && menuCardIndex.value == index,
                        onDismissRequest = {
                            menuCardIndex.value = null
                        }) {
                        DropdownMenuItem(
                            text = {
                                Text("Удалить")
                            },
                            onClick = {
                                onEvent(FinishedTaskListEvent.FinishTask(finishedTaskListState[index]))
                                menuCardIndex.value = null
                            }
                        )
                    }
                }
            }
        }
    }
}