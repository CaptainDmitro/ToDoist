package ru.captaindmitro.todoist.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.captaindmitro.todoist.domain.models.TodoDomain
import java.sql.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoItem(
    todoItem: TodoDomain,
    navToDetails: () -> Unit,
    removeTodo: (TodoDomain) -> Unit,
    isChecked: Boolean = false
) {
    Card(
        onClick = navToDetails,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = "${todoItem.title}: ${todoItem.id}",
                style = MaterialTheme.typography.titleMedium,
            )
            IconButton(
                onClick = { removeTodo(todoItem) },
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }
        Text(
            text = "${todoItem.date}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoItemPreview() {
    Column {
        repeat(10) {
            ToDoItem(
                todoItem = TodoDomain(1, "", "", Date(1L)),
                navToDetails = { },
                {},
            )
        }
    }
}