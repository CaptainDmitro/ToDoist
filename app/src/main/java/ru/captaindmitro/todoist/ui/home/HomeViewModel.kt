package ru.captaindmitro.todoist.ui.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.captaindmitro.todoist.domain.models.TodoDomain
import ru.captaindmitro.todoist.domain.common.Result
import ru.captaindmitro.todoist.domain.usecase.AddTodoItemUseCase
import ru.captaindmitro.todoist.domain.usecase.GetAllTodosUseCase
import ru.captaindmitro.todoist.domain.usecase.RemoveTodoItemUseCase
import ru.captaindmitro.todoist.ui.common.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val addTodoItemUseCase: AddTodoItemUseCase,
    private val removeTodoItemUseCase: RemoveTodoItemUseCase
): ViewModel() {

    private val _todoItems: MutableStateFlow<UiState<List<TodoDomain>>> = MutableStateFlow(UiState.Empty)
    var todoItems: StateFlow<UiState<List<TodoDomain>>> = _todoItems.asStateFlow()

    init {
        getAllTodos()
    }

    private fun getAllTodos() {
        _todoItems.value = UiState.Loading
        viewModelScope.launch {
            getAllTodosUseCase.execute().distinctUntilChanged().collectLatest { todos ->
                when (todos) {
                    is Result.Success -> {
                        _todoItems.value = if (todos.data.isEmpty()) UiState.Empty else UiState.Success(todos.data)
                    }
                    is Result.Failure -> _todoItems.value = UiState.Failure(todos.error)
                }
            }
        }
    }

    fun addTodoItem(title: String, body: String, color: Color) {
        viewModelScope.launch {
            addTodoItemUseCase.addTodoItem(title, body, color)
        }
    }

    fun removeTodoItem(todoDomain: TodoDomain) {
        viewModelScope.launch {
            removeTodoItemUseCase.execute(todoDomain)
        }
    }
}