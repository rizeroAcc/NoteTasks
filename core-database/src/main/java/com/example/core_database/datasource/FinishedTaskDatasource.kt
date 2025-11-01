package com.example.core_database.datasource

interface FinishedTaskDatasource {
    suspend fun addNewFinishedTask()
    suspend fun getFinishedTaskByID()
    suspend fun getAllFinishedTasks()
}