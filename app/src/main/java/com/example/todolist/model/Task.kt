package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo
    var name: String? = null,
    @ColumnInfo
    var description: String? = null,
    @ColumnInfo
    var dateTime: Long? = null,
    @ColumnInfo
    var isDone: Boolean = false
)
