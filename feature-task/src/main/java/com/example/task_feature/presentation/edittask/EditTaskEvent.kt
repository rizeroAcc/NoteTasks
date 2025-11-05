package com.example.task_feature.presentation.edittask

import com.example.core_models.domain.Task

sealed class EditTaskEvent {
    class UpdateTaskCard() : EditTaskEvent()
    class FinishTask(val finishedAsUnimportant : Boolean) : EditTaskEvent()
    class ChangeTaskState(val newTaskInfo : Task) : EditTaskEvent()
}