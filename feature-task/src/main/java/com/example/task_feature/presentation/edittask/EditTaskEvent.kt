package com.example.task_feature.presentation.edittask

import com.example.core_models.domain.Task

sealed class EditTaskEvent {
    class UpdateTaskInfo(val onTaskChanged : ()->Unit) : EditTaskEvent()
    class FinishTask(
        val finishedAsUnimportant : Boolean,
        val onTaskChanged : ()->Unit
    ) : EditTaskEvent()
    class ChangeTaskState(
        val newTaskInfo : Task,
    ) : EditTaskEvent()
}