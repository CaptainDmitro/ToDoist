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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoldersRow(
    vararg folders: String,
    modifier: Modifier = Modifier
) {

    val state = rememberLazyListState()
    var selected by remember { mutableStateOf("") }

    LazyRow(
        state = state,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
        modifier = modifier
    ) {
        items(folders) { folder ->
            InputChip(
                selected = selected == folder,
                onClick = { if (selected == folder) selected = "" else selected = folder; Log.i("Main", "$selected") },
                label = { Text(text = folder) },
                leadingIcon = { Icon(imageVector = Icons.Default.Done, contentDescription = "") },
            )
        }
    }
}