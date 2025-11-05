package com.example.tasklist.presentation.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.repository.TaskRepository
import com.example.core_models.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TaskListScreenViewModel @Inject constructor(val taskRepository: TaskRepository) : ViewModel() {
    val taskList = MutableStateFlow<List<Task>>(mutableListOf())
    init {
        viewModelScope.launch { taskList.update { taskRepository.getAllTasks() }  }
    }
    fun updateTaskList(){
        viewModelScope.launch { taskList.update { taskRepository.getAllTasks() }  }
    }
    fun handleEvent(event: TaskListScreenEvent){
        when (event){
            TaskListScreenEvent.TaskListUpdated -> updateTaskList()
        }
    }
}