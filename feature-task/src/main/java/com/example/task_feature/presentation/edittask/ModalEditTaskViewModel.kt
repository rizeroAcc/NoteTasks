package com.example.task_feature.presentation.edittask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_feature.data.repository.TaskRepository
import com.example.task_feature.domain.Task
import com.example.task_feature.domain.TaskCategory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel(assistedFactory = ModalEditTaskViewModel.Factory::class)
class ModalEditTaskViewModel@AssistedInject constructor(
    val taskRepository : TaskRepository,
    @Assisted private val taskID : Long,
) : ViewModel() {
    val taskState = MutableStateFlow<Task>(
        Task(0, "", "", null, TaskCategory.UNSPECIFIED)
    )
    init {
        viewModelScope.launch {
            taskState.value = taskRepository.getTask(id = taskID)
        }
    }
    fun handleEvent(event : ModalEditTaskEvent){
        //todo дожидаться завершения операций, а не оставлять корутины
        when(event){
            is ModalEditTaskEvent.FinishTask -> CoroutineScope(EmptyCoroutineContext).launch {
                taskRepository.finishTask(
                    task = taskState.value,
                    finishTime = Instant.now(),
                    finishedAsUnImportant = event.finishedAsUnimportant
                )
            }

            is ModalEditTaskEvent.UpdateTaskCard -> {
                CoroutineScope(EmptyCoroutineContext).launch {
                    taskRepository.updateTask(task = taskState.value)
                }
            }

            is ModalEditTaskEvent.ChangeTaskState -> taskState.update { event.newTaskInfo }
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(taskID: Long): ModalEditTaskViewModel
    }
}