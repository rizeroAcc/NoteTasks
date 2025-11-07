package com.example.core_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_database.entity.FinishedTaskEntity

@Dao
interface FinishedTaskDAO {
    @Delete
    suspend fun deleteFinishedTask(finishedTask: FinishedTaskEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFinishedTask(finishedTask : FinishedTaskEntity)
    @Query("""SELECT * FROM finishedTasks""")
    suspend fun getAllFinishedTasks() : List<FinishedTaskEntity>
    @Query("""SELECT * FROM finishedTasks WHERE id =:id""")
    suspend fun getFinishedTaskByID(id : Long) : FinishedTaskEntity
}