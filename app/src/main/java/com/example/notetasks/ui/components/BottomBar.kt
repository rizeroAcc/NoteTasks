package com.example.notetasks.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.core_navigation.Navigator
import com.example.tasklist.presentation.FinishedTaskListScreen
import com.example.tasklist.presentation.TaskListScreen

@Composable
fun BottomBar(
    navigator: Navigator,
    currentDestination: Any?
) {
    NavigationBar {
        // Кнопка Task List
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "desc"
                )
            },
            label = { Text("Current tasks") },
            selected = currentDestination == TaskListScreen,
            onClick = {
                if (currentDestination != TaskListScreen) {
                    navigator.goTo(TaskListScreen)
                }
            }
        )

        // Кнопка Finished Tasks
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "stringResource(R.string.finished_tasks)"
                )
            },
            label = { Text("Finished tasks") },
            selected = currentDestination == FinishedTaskListScreen,
            onClick = {
                if (currentDestination != FinishedTaskListScreen) {
                    navigator.goTo(FinishedTaskListScreen)
                }
            }
        )
    }
}