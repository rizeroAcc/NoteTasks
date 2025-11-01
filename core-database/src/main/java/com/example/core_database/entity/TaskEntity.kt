package com.example.core_database.entity

import android.app.ActivityManager
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    @SerialName("ID")
    val id : Long,
    @SerialName("taskName")
    val taskName : String,
    @SerialName("description")
    val taskDescription: String,
    @SerialName("category")
    val taskCategory : String,
    @SerialName("deadline")
    val deadline : Long? = null,
)