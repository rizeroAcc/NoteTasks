package com.example.task_feature.data.repository

import com.example.core_database.datasource.FinishedTaskDatasource
import com.example.core_database.datasource.TaskDatasource
import com.example.task_feature.domain.Task
import com.example.task_feature.util.toDatabaseEntity
import com.example.task_feature.util.toDomain
import com.example.task_feature.util.toFinishedTaskEntity
import java.time.Instant
import javax.inject.Inject

class TaskRepository @Inject constructor(
    val taskDatasource: TaskDatasource,
    val finishedTaskDatasource: FinishedTaskDatasource,
) {
    suspend fun createTask(task : Task){
        taskDatasource.createTask(task.toDatabaseEntity())
    }
    @Deprecated("Need do it in transaction")
    suspend fun finishTask(task : Task, finishTime : Instant, finishedAsUnImportant : Boolean){
        //todo do it in transaction
        taskDatasource.deleteTask(task.toDatabaseEntity())
        finishedTaskDatasource.addNewFinishedTask(task.toFinishedTaskEntity(
            finishTime = finishTime, finishedAsUnimportant = finishedAsUnImportant
        ))
    }
    suspend fun updateTask(task: Task){
        taskDatasource.updateTask(task.toDatabaseEntity())
    }
    suspend fun getAllTasks() : List<Task>{
        return taskDatasource.getAllTasks().map { it.toDomain() }
    }
    suspend fun getTask(id : Long) : Task{
        val task = taskDatasource.getTaskByID(id)
        return task?.toDomain() ?: throw IllegalStateException()
    }
}