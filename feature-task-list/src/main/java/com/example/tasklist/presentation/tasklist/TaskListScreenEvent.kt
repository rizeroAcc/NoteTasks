package com.example.tasklist.presentation.tasklist

sealed class TaskListScreenEvent {
    object TaskListUpdated : TaskListScreenEvent()
}