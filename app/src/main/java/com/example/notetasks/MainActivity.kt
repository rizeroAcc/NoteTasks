package com.example.notetasks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.NavEvent
import com.example.core_navigation.Navigator
import com.example.tasklist.presentation.FinishedTaskListScreen
import com.example.notetasks.ui.components.BottomBar
import com.example.notetasks.ui.components.ModalNavigation
import com.example.notetasks.ui.theme.NoteTasksTheme
import com.example.task_feature.presentation.createtask.ModalCreateTaskCardKey
import com.example.task_feature.presentation.edittask.ModalEditTaskCardKey
import com.example.tasklist.presentation.TaskListScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator : Navigator

    @Inject
    lateinit var entryProviderScopes: Set<@JvmSuppressWildcards EntryProviderInstaller>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        navigator.setEventHandler { event ->
            when (event) {
                NavEvent.NavBack -> navigator.goBack()
                NavEvent.NavToFinishedTaskList -> navigator.goTo(FinishedTaskListScreen)
                NavEvent.NavToCurrentTaskList -> navigator.goTo(TaskListScreen)
                NavEvent.HideAllModal -> navigator.hideAllModalWindows()
                NavEvent.HideModal -> navigator.hideModalWindow()
                is NavEvent.ShowTaskCard -> navigator.showModal(ModalEditTaskCardKey(0))
                NavEvent.ShowCreateTaskModal -> navigator.showModal(ModalCreateTaskCardKey)
            }
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigator.goBack()
            }
        })
        setContent {
            NoteTasksTheme {
                val currentDestination = navigator.backStack.lastOrNull()
                val currentModal = navigator.modalWindowBackStack.lastOrNull()
                Scaffold(
                    bottomBar = {
                        if (navigator.modalWindowBackStack.isEmpty()){
                            BottomBar(navigator = navigator, currentDestination = currentDestination)
                        }
                    }
                )
                { innerPadding->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = navigator.backStack,
                        onBack = {
                            Log.d("back","back")
                            navigator.goBack()
                                 },
                        entryProvider = entryProvider {
                            entryProviderScopes.forEach { builder -> this.builder() }
                        },
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator()
                        )
                    )
                    if (currentModal != null) {
                        ModalNavigation(
                            modifier = Modifier.padding(innerPadding),
                            backStack = navigator.modalWindowBackStack,
                            onDismiss = { navigator.handleEvent(NavEvent.HideModal) },
                            entryProvider = entryProvider {
                                entryProviderScopes.forEach { builder -> this.builder() }
                            }
                        )
                    }
                }
            }
        }
    }
}

