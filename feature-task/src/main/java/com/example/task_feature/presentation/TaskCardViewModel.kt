package com.example.task_feature.presentation

import androidx.lifecycle.ViewModel
import com.example.task_feature.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskCardViewModel@Inject constructor(
    val taskRepository : TaskRepository
) : ViewModel() {

}