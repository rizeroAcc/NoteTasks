package com.example.tasklist.finished_tasklist

import com.example.core_models.domain.FinishedTask

sealed class FinishedTaskListEvent {
    class FinishTask(val task: FinishedTask) : FinishedTaskListEvent()
}