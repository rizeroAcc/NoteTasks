package com.example.task_feature.di

import com.example.core_database.TaskDatabase
import com.example.core_database.dao.FinishedTaskDAO
import com.example.core_database.dao.TaskDAO
import com.example.core_database.datasource.FinishedTaskDatasource
import com.example.core_database.datasource.TaskDatasource
import com.example.task_feature.data.datasource.FinishedTaskDatasourceImpl
import com.example.task_feature.data.datasource.TaskDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class Datasource {
    @Provides
    fun provideTaskDatasource(taskDAO: TaskDAO) : TaskDatasource{
        return TaskDatasourceImpl(taskDAO = taskDAO)
    }
    @Provides
    fun provideFinishedTaskDatasource(finishedTaskDAO: FinishedTaskDAO) : FinishedTaskDatasource {
        return FinishedTaskDatasourceImpl(finishedTaskDAO = finishedTaskDAO)
    }
}