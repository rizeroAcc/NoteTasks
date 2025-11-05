package com.example.task_feature.presentation.createtask

import com.example.core_models.domain.Task

sealed class CreateTaskEvent {
    class ChangeTaskState(val newTaskInfo : Task) : CreateTaskEvent()
    class CreateTask(val onTaskCreated : ()->Unit) : CreateTaskEvent()
}