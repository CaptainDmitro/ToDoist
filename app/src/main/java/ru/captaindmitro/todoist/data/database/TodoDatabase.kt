package ru.captaindmitro.todoist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.captaindmitro.todoist.data.model.TodoData

@Database(entities = [TodoData::class], version = 4)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

}