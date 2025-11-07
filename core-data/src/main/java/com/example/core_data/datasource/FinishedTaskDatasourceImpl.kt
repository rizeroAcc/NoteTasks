package com.example.core_data.datasource

import com.example.core_database.dao.FinishedTaskDAO
import com.example.core_database.datasource.FinishedTaskDatasource
import com.example.core_database.entity.FinishedTaskEntity
import javax.inject.Inject

class FinishedTaskDatasourceImpl @Inject constructor(
    val finishedTaskDAO: FinishedTaskDAO
) : FinishedTaskDatasource {
    override suspend fun deleteFinishedTaskInfo(finishedTask: FinishedTaskEntity) {
        finishedTaskDAO.deleteFinishedTask(finishedTask = finishedTask)
    }

    override suspend fun addNewFinishedTask(finishedTask: FinishedTaskEntity) =
        finishedTaskDAO.insertFinishedTask(finishedTask = finishedTask)

    override suspend fun getFinishedTaskByID(finishedTaskID: Long) =
        finishedTaskDAO.getFinishedTaskByID(finishedTaskID)

    override suspend fun getAllFinishedTasks(): List<FinishedTaskEntity> =
        finishedTaskDAO.getAllFinishedTasks()
}