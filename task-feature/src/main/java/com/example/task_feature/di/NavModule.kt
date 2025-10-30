package com.example.task_feature.di

import com.example.core_navigation.EntryProviderInstaller
import com.example.core_navigation.Navigator
import com.example.task_feature.presentation.ModalTaskCard
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
     fun provideNavGraphEntryProviderInstaller(navigator: Navigator) : EntryProviderInstaller = {
         entry<ModalTaskCard>{
             ModalTaskCard()
         }
     }
}