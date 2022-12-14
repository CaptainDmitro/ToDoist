package ru.captaindmitro.todoist.ui.newtodo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.captaindmitro.todoist.domain.models.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesRow(
    vararg folders: String,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()
    var selected by remember { mutableStateOf(Category.None) }

    LazyRow(
        state = state,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(folders) { folder ->
            InputChip(
                selected = selected.toString() == folder,
                onClick = { if (selected.toString() == folder) selected = Category.None else selected = Category.valueOf(folder) },
                label = { Text(text = folder) }
            )
        }
    }
}