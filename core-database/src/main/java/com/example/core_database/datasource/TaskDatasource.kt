package com.example.core_database.datasource

interface TaskDatasource {
    suspend fun createTask()
    suspend fun finishTask()
    suspend fun updateTask()
    suspend fun deleteTask()
    suspend fun getTaskByID()
    suspend fun getAllTasks()
}