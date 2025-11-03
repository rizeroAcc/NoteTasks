package com.example.task_feature.domain

import java.time.Instant

data class Task(
    val id : Long = 0,
    val taskName : String,
    val taskDescription: String,
    val deadline : Instant? = null,
    val category : TaskCategory,
)
