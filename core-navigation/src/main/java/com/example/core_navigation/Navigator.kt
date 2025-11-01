package com.example.core_navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope

typealias EntryProviderInstaller = EntryProviderScope<Any>.() -> Unit

class Navigator(startDestination: Any) {
    val backStack : SnapshotStateList<Any> = mutableStateListOf(startDestination)
    val modalWindowBackStack : SnapshotStateList<Any> = mutableStateListOf()
    private var eventHandler: ((NavEvent) -> Unit)? = null

    fun setEventHandler(handler: (NavEvent) -> Unit) {
        this.eventHandler = handler
    }

    fun handleEvent(event: NavEvent) {
        eventHandler?.invoke(event)
    }

    fun hideModalWindow(){
        modalWindowBackStack.removeLastOrNull()
    }

    fun hideAllModalWindows(){
        modalWindowBackStack.clear()
    }

    fun showModal(modal : Any){
        modalWindowBackStack.add(modal)
    }

    fun goTo(destination: Any){
        backStack.add(destination)
    }

    fun goBack(){
        if (!modalWindowBackStack.isEmpty()){
            hideModalWindow()
        }else if (backStack.size > 1){
            backStack.removeLastOrNull()
        }
    }
}