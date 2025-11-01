package com.example.core_database.datasource

import com.example.core_database.entity.FinishedTaskEntity

interface FinishedTaskDatasource {
    suspend fun addNewFinishedTask(finishedTask: FinishedTaskEntity)
    suspend fun getFinishedTaskByID(finishedTaskID : Long) : FinishedTaskEntity
    suspend fun getAllFinishedTasks() : List<FinishedTaskEntity>
}