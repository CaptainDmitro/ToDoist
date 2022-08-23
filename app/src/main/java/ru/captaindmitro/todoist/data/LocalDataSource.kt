package ru.captaindmitro.todoist.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.captaindmitro.todoist.data.database.TodoDao
import ru.captaindmitro.todoist.data.model.TodoData
import javax.inject.Inject

interface LocalDataSource {

    fun getAllTodos(): Flow<List<TodoData>>
    suspend fun getTodoItemById(id: Int): TodoData
    suspend fun addTodoItem(todo: TodoData)
    suspend fun updateTodoItem(todo: TodoData)
    suspend fun deleteTodoItem(todo: TodoData)

    class Base @Inject constructor(
        private val todoDao: TodoDao,
        private val dispatcher: CoroutineDispatcher
    ) : LocalDataSource {
        override fun getAllTodos(): Flow<List<TodoData>> {
            Log.i("Main", "LDT:getAllTodos ${dispatcher}")
            return todoDao.getAllTodos().flowOn(dispatcher)
        }

        override suspend fun getTodoItemById(id: Int): TodoData {
            Log.i("Main", "LDT:getTodoById ${dispatcher}")
            return todoDao.getTodoItemById(id)
        }

        override suspend fun addTodoItem(todo: TodoData) = withContext(dispatcher) {
            Log.i("Main", "LDT:addTodoItem ${todo}")
            todoDao.addTodoItem(todo)
        }

        override suspend fun updateTodoItem(todo: TodoData) = withContext(dispatcher) {
            todoDao.updateTodoItem(todo)
        }

        override suspend fun deleteTodoItem(todo: TodoData) {
            Log.i("Main", "LDT:deleteTodoItem ${todo}")
            todoDao.deleteTodoItem(todo)
        }

    }
}