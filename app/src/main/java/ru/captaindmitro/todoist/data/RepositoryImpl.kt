package ru.captaindmitro.todoist.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.captaindmitro.todoist.data.model.toData
import ru.captaindmitro.todoist.data.model.toDomain
import ru.captaindmitro.todoist.domain.Repository
import ru.captaindmitro.todoist.domain.models.TodoDomain
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val dispatcher: CoroutineDispatcher
) : Repository {
    override fun getAllTodos(): Flow<List<TodoDomain>> = localDataSource.getAllTodos()
        .map { list ->
            list.map { item ->
                item.toDomain()
            }
        }
        .flowOn(dispatcher)

    override suspend fun addTodoItem(todo: TodoDomain) = withContext(dispatcher) {
        localDataSource.addTodoItem(todo.toData())
    }

    override suspend fun removeTodoItem(todo: TodoDomain) {
        localDataSource.deleteTodoItem(todo.toData())
    }

    override suspend fun getTodoItemById(id: Int): TodoDomain {
        return localDataSource.getTodoItemById(id).toDomain()
    }

    override suspend fun updateTodoItem(todoItem: TodoDomain) {
        localDataSource.updateTodoItem(todoItem.toData())
    }
}