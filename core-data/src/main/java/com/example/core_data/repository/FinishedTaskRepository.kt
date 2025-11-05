package com.example.core_data.repository

import com.example.core_data.util.toDomain
import com.example.core_database.datasource.FinishedTaskDatasource
import com.example.core_models.domain.FinishedTask
import javax.inject.Inject

class FinishedTaskRepository@Inject constructor(
    val finishedTaskDatasource: FinishedTaskDatasource
) {
    suspend fun getAllFinishedTask() : List<FinishedTask> =
        finishedTaskDatasource.getAllFinishedTasks().map { it.toDomain() }
    suspend fun getFinishedTask(id : Long) : FinishedTask =
        finishedTaskDatasource.getFinishedTaskByID(id).toDomain()
}