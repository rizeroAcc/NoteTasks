package com.example.task_feature.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.task_feature.data.repository.TaskRepository
import com.example.task_feature.domain.Task
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskCardViewModel@AssistedInject constructor(
    val taskRepository : TaskRepository,
    @Assisted private val taskID : Long,
) : ViewModel() {
    fun handleEvent(event : TaskEvent){

    }
    @AssistedFactory
    interface Factory {
        fun create(taskID: Long): TaskCardViewModel
    }
}

