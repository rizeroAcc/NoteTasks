package com.example.notetasks.di

import com.example.core_navigation.Navigator
import com.example.tasklist.presentation.tasklist.TaskListScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {
    @Provides
    @ActivityRetainedScoped
    fun provideNavigator() : Navigator = Navigator(startDestination = TaskListScreen)
}