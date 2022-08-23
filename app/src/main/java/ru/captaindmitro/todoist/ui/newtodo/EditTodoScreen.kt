package ru.captaindmitro.todoist.ui.newtodo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.color.colorChooser
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.captaindmitro.todoist.domain.models.Category
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
    var selectedColor by remember { mutableStateOf(Color.Unspecified) }

    var onDoneEditing: () -> Unit = {
        homeViewModel.addTodoItem(title, body, selectedColor)
        navController.navigateUp()
    }
    var additionalGoalsCount by remember { mutableStateOf(0) }
    val additionalGoals by remember { mutableStateOf(mutableMapOf<Int, String>()) }

    val colorPickerState = rememberMaterialDialogState()

    if (editMode) {
        val todoItem = detailViewModel.todoItem.collectAsState()
        when (val state = todoItem.value) {
            is UiState.Success -> {
                title = state.data.title
                body = state.data.title
                onDoneEditing = {
                    detailViewModel.updateTodoItem(state.data, title, body)
                    navController.navigateUp()
                }
            }
            else -> { /*TODO*/ }
        }
    }

    MaterialDialog(
        dialogState = colorPickerState,
        backgroundColor = MaterialTheme.colorScheme.surface,
        buttons = {
            positiveButton(text = "Select")
            negativeButton(
                text = "Reset",
                onClick = { selectedColor = Color.Unspecified }
            )
        }
    ) {
        colorChooser(colors = ColorPalette.Primary, onColorSelected = { color -> selectedColor = color })
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onDoneEditing
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "")
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CategoriesRow(Category.Public.toString(), Category.Private.toString(), modifier = Modifier.padding(top = 8.dp))
            Box(modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
                .background(color = selectedColor, shape = CircleShape)
                .border(border = BorderStroke(1.dp, Color.Black), shape = CircleShape)
                .clickable { colorPickerState.show() }
            )
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
                    .padding(start = 8.dp, end = 8.dp)
            )
            repeat(additionalGoalsCount) { pos ->
                OutlinedTextField(
                    value = additionalGoals[pos] ?: "",
                    onValueChange = { text -> additionalGoals[pos] = text },
                    label = { Text(text = "Additional goal $pos") },
                    trailingIcon = {
                        IconButton(onClick = { additionalGoals.remove(pos); additionalGoalsCount-- }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
            TextButton(onClick = { additionalGoalsCount++ }) {
                Text(text = "Add new goal")
            }
        }
    }
}