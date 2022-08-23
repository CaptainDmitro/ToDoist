package ru.captaindmitro.todoist.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.captaindmitro.todoist.di.IoDispatcher
import ru.captaindmitro.todoist.domain.Repository
import ru.captaindmitro.todoist.domain.TodoDomain
import java.sql.Date
import javax.inject.Inject

class AddTodoItemUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun addTodoItem(title: String, body: String) = withContext(dispatcher) {
        repository.addTodoItem(
            TodoDomain(
                0, title, body, Date(System.currentTimeMillis())
            )
        )
    }

}