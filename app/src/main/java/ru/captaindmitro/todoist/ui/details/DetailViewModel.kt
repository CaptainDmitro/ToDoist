package ru.captaindmitro.todoist.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.captaindmitro.todoist.domain.TodoDomain
import ru.captaindmitro.todoist.domain.usecase.GetTodoItemByIdUseCase
import ru.captaindmitro.todoist.ui.common.UiState
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getTodoItemByIdUseCase: GetTodoItemByIdUseCase
) : ViewModel() {

    private val _todoItem: MutableStateFlow<UiState<TodoDomain>> = MutableStateFlow(UiState.Empty)
    val todoItem: StateFlow<UiState<TodoDomain>> = _todoItem.asStateFlow()

    fun getTodoItemById(id: Int) {
        viewModelScope.launch {
            _todoItem.value = UiState.Success(getTodoItemByIdUseCase.execute(id))
        }
    }

}