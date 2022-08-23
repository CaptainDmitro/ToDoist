package ru.captaindmitro.todoist.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllTodos(): Flow<List<TodoDomain>>
    suspend fun getTodoItemById(id: Int): TodoDomain
    suspend fun addTodoItem(todo: TodoDomain)
    suspend fun removeTodoItem(todo: TodoDomain)
    suspend fun unpadeTodoItem()
}