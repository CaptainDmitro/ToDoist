package ru.captaindmitro.todoist.ui.newtodo

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.captaindmitro.todoist.ui.common.UiState
import ru.captaindmitro.todoist.ui.details.DetailViewModel
import ru.captaindmitro.todoist.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTodoScreen(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    navController: NavController,
    editSelectedItem: Boolean?
) {
    val editMode = editSelectedItem ?: false
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var onDone: () -> Unit = {
        homeViewModel.addTodoItem(title, body)
        navController.navigateUp()
    }

    if (editMode) {
        val todoItem = detailViewModel.todoItem.collectAsState()
        when (val state = todoItem.value) {
            is UiState.Success -> {
                title = state.data.title
                body = state.data.title
                onDone = {
                    detailViewModel.updateTodoItem(state.data, title, body)
                    navController.navigateUp()
                }
            }
            else -> {}
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onDone
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            FoldersRow("Important", "Private", "Custom", "Another")
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            )
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text(text = "Note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(start = 8.dp, end = 8.dp)
            )
        }
    }


}