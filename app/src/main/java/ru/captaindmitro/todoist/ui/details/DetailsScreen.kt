package ru.captaindmitro.todoist.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.captaindmitro.todoist.domain.TodoDomain
import ru.captaindmitro.todoist.ui.common.UiState
import ru.captaindmitro.todoist.ui.home.ToDoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    detailViewModel: DetailViewModel,
    todoItemId: Int?
) {
    LaunchedEffect(Unit) {
        todoItemId?.let { id ->
            detailViewModel.getTodoItemById(id)
        }
    }

    val todoItem = detailViewModel.todoItem.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }
        }
    ) {
        when (val state = todoItem.value) {
            is UiState.Empty -> {
                Text(text = "Empty", modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center))
            }
            is UiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is UiState.Success -> {
                Column {
                    Text(text = state.data.title)
                    Text(text = state.data.body)
                }
            }
            is UiState.Failure -> {
                Text(text = "Failure: ${state.error}", modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(
                        Alignment.Center
                    ))
            }
        }
    }
}