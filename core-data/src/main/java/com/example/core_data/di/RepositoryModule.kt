package com.example.core_data.di

import com.example.core_data.repository.FinishedTaskRepository
import com.example.core_data.repository.TaskRepository
import com.example.core_database.datasource.FinishedTaskDatasource
import com.example.core_database.datasource.TaskDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {
    @Provides
    fun provideTaskRepository(
        taskDatasource: TaskDatasource,
        finishedTaskDatasource: FinishedTaskDatasource
    ) : TaskRepository = TaskRepository(
        taskDatasource = taskDatasource,
        finishedTaskDatasource = finishedTaskDatasource
    )

    @Provides
    fun provideFinishedTaskRepository(
        finishedTaskDatasource : FinishedTaskDatasource
    ) : FinishedTaskRepository = FinishedTaskRepository(
        finishedTaskDatasource = finishedTaskDatasource
    )

}