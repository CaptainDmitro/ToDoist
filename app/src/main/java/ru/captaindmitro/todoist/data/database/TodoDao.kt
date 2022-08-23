package ru.captaindmitro.todoist.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.captaindmitro.todoist.data.model.TodoData

@Dao
interface TodoDao {

    @Query("SELECT * FROM tododata")
    fun getAllTodos(): Flow<List<TodoData>>

    @Query("SELECT * FROM tododata WHERE id = :todoId")
    suspend fun getTodoItemById(todoId: Int): TodoData

    @Insert
    suspend fun addTodoItem(todo: TodoData)

    @Delete
    suspend fun deleteTodoItem(todo: TodoData)

}