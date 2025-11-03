package com.example.task_feature.presentation.edittask

import com.example.task_feature.domain.Task

sealed class ModalEditTaskEvent {
    class UpdateTaskCard() : ModalEditTaskEvent()
    class FinishTask(val finishedAsUnimportant : Boolean) : ModalEditTaskEvent()
    class ChangeTaskState(val newTaskInfo : Task) : ModalEditTaskEvent()
}