package ru.captaindmitro.todoist.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.captaindmitro.todoist.domain.TodoDomain
import ru.captaindmitro.todoist.domain.common.Result
import ru.captaindmitro.todoist.domain.usecase.AddTodoItemUseCase
import ru.captaindmitro.todoist.domain.usecase.GetAllTodosUseCase
import ru.captaindmitro.todoist.domain.usecase.RemoveTodoItemUseCase
import ru.captaindmitro.todoist.ui.common.UiState
import java.sql.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val addTodoItemUseCase: AddTodoItemUseCase,
    private val removeTodoItemUseCase: RemoveTodoItemUseCase,
): ViewModel() {

    private val _todoItems: MutableStateFlow<UiState<List<TodoDomain>>> = MutableStateFlow(UiState.Empty)
    var todoItems: StateFlow<UiState<List<TodoDomain>>> = _todoItems.asStateFlow()

    init {
        getAllTodos()
    }

    fun getAllTodos() {
        _todoItems.value = UiState.Loading
        viewModelScope.launch {
            getAllTodosUseCase.execute().distinctUntilChanged().collectLatest { todos ->
                Log.i("Main", "Collected $todos")
                when (todos) {
                    is Result.Success -> {
                        _todoItems.value = if (todos.data.isEmpty()) UiState.Empty else UiState.Success(todos.data)
                        Log.i("Main", "SF = ${_todoItems.value::class.java}")
                    }
                    is Result.Failure -> _todoItems.value = UiState.Failure(todos.error)
                }
            }
        }
    }

    fun addTodoItem(title: String, body: String) {
        viewModelScope.launch {
            addTodoItemUseCase.addTodoItem(title, body)
        }
    }

    fun removeTodoItem(todoDomain: TodoDomain) {
        viewModelScope.launch {
            Log.i("Main", "Deleting in ${this.coroutineContext}")
            removeTodoItemUseCase.execute(todoDomain)
        }
    }

}