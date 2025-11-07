package com.example.core_models.domain

import java.time.Instant

data class FinishedTask(
    val id : Long,
    val taskName : String,
    val taskDescription: String,
    val deadline : Instant? = null,
    val taskCategory : TaskCategory,
    val finishTimestamp : Instant,
    val finishedInTime : Boolean,
    val finishedAsUnimportant : Boolean,
)
