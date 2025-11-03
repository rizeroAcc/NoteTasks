package com.example.task_feature.data.repository

import com.example.core_database.datasource.FinishedTaskDatasource
import com.example.task_feature.domain.FinishedTask
import com.example.task_feature.util.toDomain
import javax.inject.Inject

class FinishedTaskRepository@Inject constructor(
    val finishedTaskDatasource: FinishedTaskDatasource
) {
    suspend fun getAllFinishedTask() : List<FinishedTask> =
        finishedTaskDatasource.getAllFinishedTasks().map { it.toDomain() }
    suspend fun getFinishedTask(id : Long) : FinishedTask =
        finishedTaskDatasource.getFinishedTaskByID(id).toDomain()
}