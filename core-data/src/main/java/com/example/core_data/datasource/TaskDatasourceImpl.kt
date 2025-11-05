package com.example.core_data.datasource

import com.example.core_database.dao.TaskDAO
import com.example.core_database.datasource.TaskDatasource
import com.example.core_database.entity.TaskEntity
import javax.inject.Inject

class TaskDatasourceImpl @Inject constructor(
    val taskDAO: TaskDAO
) : TaskDatasource {
    override suspend fun createTask(task: TaskEntity) = taskDAO.insertTask(taskEntity = task)

    override suspend fun updateTask(task: TaskEntity) = taskDAO.updateTask(taskEntity = task)

    override suspend fun deleteTask(task: TaskEntity) = taskDAO.deleteTask(taskEntity = task)

    override suspend fun getTaskByID(taskID: Long) = taskDAO.getTaskByID(taskID)

    override suspend fun getAllTasks(): List<TaskEntity> = taskDAO.getAllTasks()
}