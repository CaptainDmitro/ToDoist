package ru.captaindmitro.todoist.domain.usecase

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.captaindmitro.todoist.di.IoDispatcher
import ru.captaindmitro.todoist.domain.Repository
import ru.captaindmitro.todoist.domain.models.TodoDomain
import java.sql.Date
import javax.inject.Inject

class AddTodoItemUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun addTodoItem(title: String, body: String, color: Color) = withContext(dispatcher) {
        repository.addTodoItem(
            TodoDomain(
                id = 0,
                title = title,
                body = body,
                date = Date(System.currentTimeMillis()),
                color = color
            )
        )
    }

}