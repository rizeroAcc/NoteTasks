package com.example.task_feature.presentation.edittask

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.repository.TaskRepository
import com.example.core_models.domain.Task
import com.example.core_models.domain.TaskCategory
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
        Log.d("VM", "Created")
        viewModelScope.launch {
            taskState.value = taskRepository.getTask(id = taskID)
        }
    }
    fun handleEvent(event : EditTaskEvent){
        //todo дожидаться завершения операций, а не оставлять корутины
        when(event){
            is EditTaskEvent.FinishTask -> CoroutineScope(EmptyCoroutineContext).launch {
                taskRepository.finishTask(
                    task = taskState.value,
                    finishTime = Instant.now(),
                    finishedAsUnImportant = event.finishedAsUnimportant
                )
            }

            is EditTaskEvent.UpdateTaskCard -> {
                CoroutineScope(EmptyCoroutineContext).launch {
                    taskRepository.updateTask(task = taskState.value)
                }
            }

            is EditTaskEvent.ChangeTaskState -> taskState.update { event.newTaskInfo }
        }
    }

    override fun onCleared() {
        Log.d("VM","VM destroyed")
        super.onCleared()
    }
    @AssistedFactory
    interface Factory {
        fun create(taskID: Long): ModalEditTaskViewModel
    }
}