package ru.captaindmitro.todoist.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.captaindmitro.todoist.domain.models.TodoDomain
import ru.captaindmitro.todoist.domain.usecase.GetTodoItemByIdUseCase
import ru.captaindmitro.todoist.domain.usecase.UpdateTodoItemUseCase
import ru.captaindmitro.todoist.ui.common.UiState
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getTodoItemByIdUseCase: GetTodoItemByIdUseCase,
    private val updateTodoItemUseCase: UpdateTodoItemUseCase
) : ViewModel() {

    private val _todoItem: MutableStateFlow<UiState<TodoDomain>> = MutableStateFlow(UiState.Empty)
    val todoItem: StateFlow<UiState<TodoDomain>> = _todoItem.asStateFlow()

    fun getTodoItemById(id: Int) {
        viewModelScope.launch {
            try {
                val res = getTodoItemByIdUseCase.execute(id)
                _todoItem.value = UiState.Success(res)
            } catch (e: Exception) {
                _todoItem.value = UiState.Failure(e)
            }
        }
    }

    fun updateTodoItem(todoItem: TodoDomain, newTitle: String, newBody: String) {
        viewModelScope.launch {
            updateTodoItemUseCase.execute(
                todoItem.copy(title = newTitle, body = newBody)
            )
        }
    }

}