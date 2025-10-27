package com.example.core_navigation

sealed class NavEvent {
    object NavBack : NavEvent()
    //Events
    object NavToCurrentTaskList : NavEvent()
    object NavToFinishedTaskList : NavEvent()

}