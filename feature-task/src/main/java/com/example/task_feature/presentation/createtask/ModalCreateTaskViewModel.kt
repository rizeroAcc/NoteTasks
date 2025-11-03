package com.example.task_feature.presentation.createtask

import androidx.lifecycle.ViewModel
import com.example.task_feature.data.repository.TaskRepository
import com.example.task_feature.domain.Task
import com.example.task_feature.domain.TaskCategory
import com.example.task_feature.presentation.edittask.ModalEditTaskEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class ModalCreateTaskViewModel @Inject constructor(
    val taskRepository: TaskRepository
) : ViewModel() {
    val taskState = MutableStateFlow<Task>(
        Task(0, "", "", null, TaskCategory.UNSPECIFIED)
    )

    fun handleEvent(event : CreateTaskEvent){
        //todo дожидаться завершения операций, а не оставлять корутины
        when(event){
            is CreateTaskEvent.ChangeTaskState -> {
                taskState.update { event.newTaskInfo }
            }
            CreateTaskEvent.CreateTask -> {
                CoroutineScope(EmptyCoroutineContext).launch {
                    taskRepository.createTask(task = taskState.value)
                }
            }
        }
    }
}