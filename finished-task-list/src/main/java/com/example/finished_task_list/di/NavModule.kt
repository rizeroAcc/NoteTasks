package com.example.finished_task_list.di

import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.Navigator
import com.example.finished_task_list.presentation.FinishedTaskListScreen
import com.example.finished_task_list.presentation.FinishedTaskScreen
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
    fun provideNavGraphProviderInstaller(navigator : Navigator) : EntryProviderInstaller = {
        entry<FinishedTaskListScreen>{
            FinishedTaskScreen(){
                navigator.goBack()
            }
        }
    }
}