package com.example.core_navigation

sealed class  NavEvent {
    object NavBack : NavEvent()
    //Events
    object NavToCurrentTaskList : NavEvent()
    object NavToFinishedTaskList : NavEvent()
    class ShowEditTaskModal(
        val taskID : Long,
        val onTaskUpdated : (()->Unit)? = null
    ) : NavEvent()
    class ShowCreateTaskModal(val onTaskCreated : (()->Unit)? = null) : NavEvent()
}