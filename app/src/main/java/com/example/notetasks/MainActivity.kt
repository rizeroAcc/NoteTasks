package com.example.notetasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.NavEvent
import com.example.core_navigation.Navigator
import com.example.finished_task_list.presentation.FinishedTaskListScreen
import com.example.notetasks.ui.components.BottomBar
import com.example.notetasks.ui.theme.NoteTasksTheme
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
                is NavEvent.NavBack -> navigator.goBack()
                is NavEvent.NavToFinishedTaskList -> navigator.goTo(FinishedTaskListScreen)
                is NavEvent.NavToCurrentTaskList -> navigator.goTo(TaskListScreen)
            }
        }

        setContent {
            NoteTasksTheme {
                val currentDestination = navigator.backStack.lastOrNull()
                Scaffold(
                    bottomBar = {
                        BottomBar(navigator = navigator, currentDestination = currentDestination)
                    }
                )
                { innerPadding->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = navigator.backStack,
                        onBack = { navigator.goBack() },
                        entryProvider = entryProvider {
                            entryProviderScopes.forEach { builder -> this.builder() }
                        }
                    )
                }
            }
        }
    }
}

