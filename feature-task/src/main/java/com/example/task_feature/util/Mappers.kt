package com.example.task_feature.util

import com.example.core_database.entity.FinishedTaskEntity
import com.example.core_database.entity.TaskEntity
import com.example.task_feature.domain.FinishedTask
import com.example.task_feature.domain.Task
import com.example.task_feature.domain.TaskCategory
import java.time.Instant

fun Task.toDatabaseEntity() : TaskEntity = TaskEntity(
    id = id,
    taskCategory = category.toString(),
    taskDescription = taskDescription,
    deadline = deadline?.toEpochMilli(),
    taskName = taskName
)

fun Task.toFinishedTaskEntity(finishTime : Instant, finishedAsUnimportant : Boolean) : FinishedTaskEntity = FinishedTaskEntity(
    id = id,
    taskName = taskName,
    taskDescription = taskDescription,
    deadline = deadline?.toEpochMilli(),
    taskCategory = category.toString(),
    finishTimestamp = finishTime.toEpochMilli(),
    finishedInTime = finishedAsUnimportant || deadline?.isBefore(finishTime) ?: true,
    finishedAsUnimportant = finishedAsUnimportant,
)

fun TaskEntity.toDomain() : Task = Task(
    id = id,
    taskName = taskName,
    taskDescription = taskDescription,
    deadline = if (deadline!= null ) Instant.ofEpochMilli(deadline!!) else null,
    category = TaskCategory.fromString(taskCategory)
)

fun FinishedTaskEntity.toDomain() : FinishedTask = FinishedTask(
    id = id,
    taskName = taskName,
    taskDescription = taskDescription,
    deadline = (if (deadline != null) Instant.ofEpochMilli(deadline!!) else null),
    taskCategory = taskCategory,
    finishTimestamp = finishTimestamp,
    finishedInTime = finishedInTime,
    finishedAsUnimportant = finishedAsUnimportant
)