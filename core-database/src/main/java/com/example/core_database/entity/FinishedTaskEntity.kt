package com.example.core_database.entity

import android.app.ActivityManager
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity("finishedTasks")
data class FinishedTaskEntity(
    @PrimaryKey
    @SerialName("ID")
    val id : Long,
    @SerialName("taskName")
    val taskName : String,
    @SerialName("description")
    val taskDescription: String,
    @SerialName("deadline")
    val deadline : Long? = null,
    @SerialName("category")
    val taskCategory : String,
    @SerialName("finishTime")
    val finishTimestamp : Long,
    @SerialName("finishedInTime")
    val finishedInTime : Boolean,
    @SerialName("unimportant")
    val finishedAsUnimportant : Boolean,
)
