package com.example.core_database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {
}