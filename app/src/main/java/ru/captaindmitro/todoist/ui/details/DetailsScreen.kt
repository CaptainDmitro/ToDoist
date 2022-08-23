package ru.captaindmitro.todoist.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.captaindmitro.todoist.ui.common.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    detailViewModel: DetailViewModel,
    navController: NavController,
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
            FloatingActionButton(onClick = {
                navController.navigate("newtodo?editSelectedItem=${true}")
            }) {
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
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentSize()
                ) {
                    Text(
                        text = state.data.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = state.data.body,
                        style = MaterialTheme.typography.bodyMedium
                    )
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