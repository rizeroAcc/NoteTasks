package com.example.core_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core_database.entity.TaskEntity

@Dao
interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)
    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
    @Update
    suspend fun updateTask(taskEntity: TaskEntity)
    @Query("""
        SELECT * FROM tasks
    """)
    suspend fun getAllTasks() : List<TaskEntity>
    @Query("""
        SELECT * FROM tasks WHERE id = :id
    """)
    suspend fun getTaskByID(id : Long) : TaskEntity?
}