package ru.captaindmitro.todoist.domain

import kotlinx.coroutines.flow.Flow
import ru.captaindmitro.todoist.domain.models.TodoDomain

interface Repository {

    fun getAllTodos(): Flow<List<TodoDomain>>
    suspend fun getTodoItemById(id: Int): TodoDomain
    suspend fun addTodoItem(todo: TodoDomain)
    suspend fun removeTodoItem(todo: TodoDomain)
    suspend fun updateTodoItem(todoItem: TodoDomain)
}