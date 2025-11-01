package com.example.core_database.datasource

import com.example.core_database.entity.TaskEntity

interface TaskDatasource {
    suspend fun createTask(task : TaskEntity)
    suspend fun finishTask(task : TaskEntity)
    suspend fun updateTask(task : TaskEntity)
    suspend fun deleteTask(task : TaskEntity)
    suspend fun getTaskByID(taskID : Long)
    suspend fun getAllTasks() : List<TaskEntity>
}