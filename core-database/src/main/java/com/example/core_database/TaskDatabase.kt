package com.example.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core_database.converters.TimeConverter
import com.example.core_database.dao.FinishedTaskDAO
import com.example.core_database.dao.TaskDAO
import com.example.core_database.entity.FinishedTaskEntity
import com.example.core_database.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        FinishedTaskEntity::class
               ],
    version = 1
)
@TypeConverters(TimeConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDAO() : TaskDAO
    abstract fun finishedTaskDAO() : FinishedTaskDAO
}