package com.example.task_feature.presentation.createtask

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.core_data.repository.TaskRepository
import com.example.core_models.domain.Task
import com.example.core_models.domain.TaskCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class ModalCreateTaskViewModel @Inject constructor(
    val taskRepository: TaskRepository
) : ViewModel() {
    init {
        Log.d("VM", "Created")
    }
    val taskState = MutableStateFlow<Task>(
        Task(0, "", "", null, TaskCategory.UNSPECIFIED)
    )

    fun handleEvent(event : CreateTaskEvent){
        //todo дожидаться завершения операций, а не оставлять корутины
        when(event){
            is CreateTaskEvent.ChangeTaskState -> {
                taskState.update { event.newTaskInfo }
            }
            is CreateTaskEvent.CreateTask -> {
                CoroutineScope(EmptyCoroutineContext).launch {
                    taskRepository.createTask(task = taskState.value)
                    event.onTaskCreated()
                }
            }
        }
    }

    override fun onCleared() {
        Log.d("VM", "Cleared")
        super.onCleared()
    }
}