package com.example.tasklist.finished_tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_data.repository.FinishedTaskRepository
import com.example.core_models.domain.FinishedTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishedTaskListViewModel @Inject constructor(
    val finishedTaskRepository: FinishedTaskRepository
) : ViewModel() {
    val finishedTaskList = MutableStateFlow<List<FinishedTask>>(emptyList())
    init {
        viewModelScope.launch {
            finishedTaskList.value = finishedTaskRepository.getAllFinishedTask()
        }
    }
    fun handleEvent(event: FinishedTaskListEvent){
        when(event){
            is FinishedTaskListEvent.FinishTask -> {
                viewModelScope.launch {
                    finishedTaskRepository.deleteFinishedTaskInfo(event.task)
                    finishedTaskList.value = finishedTaskRepository.getAllFinishedTask()
                }
            }
        }
    }
}