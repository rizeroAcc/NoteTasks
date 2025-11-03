package com.example.core_database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core_database.TaskDatabase
import com.example.core_database.dao.FinishedTaskDAO
import com.example.core_database.dao.TaskDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : TaskDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = TaskDatabase::class.java,
            name = "taskDatabase"
        ).build()
    }

    @Provides
    fun provideTaskDAO(taskDatabase: TaskDatabase) : TaskDAO {
        return taskDatabase.taskDAO()
    }
    @Provides
    fun provideFinishedTaskDAO(taskDatabase: TaskDatabase) : FinishedTaskDAO {
        return taskDatabase.finishedTaskDAO()
    }
}