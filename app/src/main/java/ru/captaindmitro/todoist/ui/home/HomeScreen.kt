package ru.captaindmitro.todoist.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.captaindmitro.todoist.domain.models.Category
import ru.captaindmitro.todoist.ui.common.UiState
import ru.captaindmitro.todoist.ui.newtodo.CategoriesRow

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val todoList = homeViewModel.todoItems.collectAsState()
    val listState = rememberLazyListState()

    when (val state = todoList.value) {
        is UiState.Empty -> {
            Text(text = "Empty", modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
            )
        }
        is UiState.Loading -> {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        is UiState.Success -> {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CategoriesRow(Category.Public.toString(), Category.Private.toString())
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "", modifier = Modifier.padding(end = 8.dp))
                    }
                }
                items(
                    items = state.data,
                    key = { item -> item.id }
                ) { item ->
                    ToDoItem(
                        todoItem = item,
                        navToDetails = { navController.navigate("details/${item.id}") },
                        removeTodo = homeViewModel::removeTodoItem,
                    )
                }
            }
        }
        is UiState.Failure -> {
            Text(text = "Failure: ${state.error}", modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
            )
        }
    }
}