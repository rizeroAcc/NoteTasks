package com.example.task_feature.di

import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.Navigator
import com.example.task_feature.presentation.ModalTaskCard
import com.example.task_feature.presentation.ModalTaskCardKey
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
         entry<ModalTaskCardKey>{ key->
             ModalTaskCard(
                 taskID = key.taskID,
                onNavigationEvent = { navEvent->
                    navigator.handleEvent(navEvent)
                }
             )
         }
     }
}