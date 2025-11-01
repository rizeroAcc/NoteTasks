package com.example.task_feature.di

import com.example.core_database.datasource.FinishedTaskDatasource
import com.example.core_database.datasource.TaskDatasource
import com.example.task_feature.data.repository.FinishedTaskRepository
import com.example.task_feature.data.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class RemositoryModule {
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