package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.dao.TasksDao
import com.example.todoapp.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        private var instance: TaskDataBase? = null
        fun getInstance(context: Context): TaskDataBase {
            if (instance == null) {
                //initialize
                instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        TaskDataBase::class.java,
                        "tasksDB"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }
}