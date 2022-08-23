package ru.captaindmitro.todoist.domain.usecase

import ru.captaindmitro.todoist.domain.Repository
import javax.inject.Inject

class GetTodoItemByIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(id: Int) = repository.getTodoItemById(id)

}