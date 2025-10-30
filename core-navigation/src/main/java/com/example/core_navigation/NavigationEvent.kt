package com.example.core_navigation

sealed class NavEvent {
    object NavBack : NavEvent()
    //Events
    object NavToCurrentTaskList : NavEvent()
    object NavToFinishedTaskList : NavEvent()
    object HideModal : NavEvent()
    object HideAllModal : NavEvent()
    object ShowTaskCard : NavEvent()
}