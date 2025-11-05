package com.example.tasklist.di

import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.NavEvent
import com.example.core_navigation.Navigator
import com.example.tasklist.presentation.FinishedTaskListScreen
import com.example.tasklist.presentation.FinishedTaskScreen
import com.example.tasklist.presentation.tasklist.TaskListScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
class NavModule {
    @IntoSet
    @Provides
    fun provideTaskListNavGraphInstallerProvider(navigator : Navigator) : EntryProviderInstaller = {
        entry<TaskListScreen> {
            TaskListScreen(
                onCardClick = {
                    navigator.handleEvent(NavEvent.ShowEditTaskModal)
                },
                onCreateTaskClick = {
                    navigator.handleEvent(NavEvent.ShowCreateTaskModal)
                }
            )
        }
        entry<FinishedTaskListScreen>{
            FinishedTaskScreen(){
                navigator.goBack()
            }
        }
    }
}