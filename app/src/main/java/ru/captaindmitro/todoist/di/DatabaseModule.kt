package ru.captaindmitro.todoist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.captaindmitro.todoist.data.database.TodoDatabase
import ru.captaindmitro.todoist.data.database.TodoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTodoDao(@ApplicationContext context: Context): TodoDao {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo"
        ).fallbackToDestructiveMigration().build().todoDao()
    }

}