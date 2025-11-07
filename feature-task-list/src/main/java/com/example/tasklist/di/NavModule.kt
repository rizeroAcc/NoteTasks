package com.example.tasklist.di

import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.Navigator
import com.example.tasklist.finished_tasklist.FinishedTaskListScreen
import com.example.tasklist.finished_tasklist.FinishedTaskScreen
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
        entry<TaskListScreen> { key->
            TaskListScreen(
                onNavigateTo = { event ->
                    navigator.handleEvent(event)
                }
            )
        }
        entry<FinishedTaskListScreen>{
            FinishedTaskScreen()
        }
    }
}