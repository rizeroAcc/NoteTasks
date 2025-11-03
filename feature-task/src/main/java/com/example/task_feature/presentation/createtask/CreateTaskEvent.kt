package com.example.task_feature.presentation.createtask

import com.example.task_feature.domain.Task
import com.example.task_feature.presentation.edittask.ModalEditTaskEvent

sealed class CreateTaskEvent {
    class ChangeTaskState(val newTaskInfo : Task) : CreateTaskEvent()
    object CreateTask : CreateTaskEvent()
}