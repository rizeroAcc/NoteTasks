package com.example.tasklist.di

import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.NavEvent
import com.example.core_navigation.Navigator
import com.example.tasklist.presentation.TaskListScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
class NavModule {
    @IntoSet
    @Provides
    fun provideTaskListNavGraphInstallerProvider(navigator : Navigator) : EntryProviderInstaller = {
        entry<TaskListScreen> {
            TaskListScreen(){
                navigator.handleEvent(NavEvent.NavToFinishedTaskList)
            }
        }
    }
}