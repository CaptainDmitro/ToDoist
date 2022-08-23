package ru.captaindmitro.todoist.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.captaindmitro.todoist.domain.models.TodoDomain
import java.sql.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoItem(
    todoItem: TodoDomain,
    navToDetails: () -> Unit,
) {
    var isDone by remember { mutableStateOf(false) }

    Card(
        onClick = navToDetails,
        enabled = !isDone,
        colors = CardDefaults.cardColors(
            containerColor = todoItem.color,
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
                text = todoItem.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    textDecoration = if (isDone) TextDecoration.LineThrough else TextDecoration.None
                )
            )
            RadioButton(selected = isDone, onClick = { isDone = !isDone })
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
                navToDetails = { }
            )
        }
    }
}