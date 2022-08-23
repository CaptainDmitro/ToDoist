package ru.captaindmitro.todoist.domain.usecase

import ru.captaindmitro.todoist.domain.Repository
import ru.captaindmitro.todoist.domain.models.TodoDomain
import javax.inject.Inject

class UpdateTodoItemUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(todoItem: TodoDomain) {
        repository.updateTodoItem(todoItem)
    }

}