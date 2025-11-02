package com.example.task_feature.di

import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.Navigator
import com.example.task_feature.presentation.createtask.ModalCreateTask
import com.example.task_feature.presentation.createtask.ModalCreateTaskCardKey
import com.example.task_feature.presentation.edittask.ModalEditTask
import com.example.task_feature.presentation.edittask.ModalEditTaskCardKey
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
     fun provideNavGraphEntryProviderInstaller(navigator: Navigator) : EntryProviderInstaller = {
         entry<ModalEditTaskCardKey>{ key->
             ModalEditTask(
                 taskID = key.taskID,
                onNavigationEvent = { navEvent->
                    navigator.handleEvent(navEvent)
                }
             )
         }
         entry<ModalCreateTaskCardKey> {
             ModalCreateTask() {  }
         }
     }
}