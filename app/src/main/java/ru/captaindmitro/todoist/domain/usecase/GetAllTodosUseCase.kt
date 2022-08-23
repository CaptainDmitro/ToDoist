package ru.captaindmitro.todoist.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import ru.captaindmitro.todoist.di.IoDispatcher
import ru.captaindmitro.todoist.domain.common.Result
import ru.captaindmitro.todoist.domain.Repository
import ru.captaindmitro.todoist.domain.models.TodoDomain
import javax.inject.Inject

class GetAllTodosUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun execute(): Flow<Result<List<TodoDomain>>> {
        return repository
            .getAllTodos()
            .map { list ->
                try {
                    Result.Success(list)
                } catch (e: Exception) {
                    Result.Failure(e)
                }
            }
            .flowOn(dispatcher)
    }
}