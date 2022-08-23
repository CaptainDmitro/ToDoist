package ru.captaindmitro.todoist.domain.usecase

import ru.captaindmitro.todoist.domain.Repository
import ru.captaindmitro.todoist.domain.TodoDomain
import javax.inject.Inject

class RemoveTodoItemUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(todoDomain: TodoDomain) {
        repository.removeTodoItem(todoDomain)
    }

}