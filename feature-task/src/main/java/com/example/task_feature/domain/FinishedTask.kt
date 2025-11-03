package com.example.task_feature.domain

import java.time.Instant

data class FinishedTask(
    val id : Long,
    val taskName : String,
    val taskDescription: String,
    val deadline : Instant? = null,
    val taskCategory : String,
    val finishTimestamp : Long,
    val finishedInTime : Boolean,
    val finishedAsUnimportant : Boolean,
)
