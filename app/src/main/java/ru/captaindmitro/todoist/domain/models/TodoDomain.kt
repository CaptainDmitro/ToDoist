package ru.captaindmitro.todoist.domain.models

import androidx.compose.ui.graphics.Color
import java.sql.Date

data class TodoDomain(
    val id: Int,
    val title: String,
    val body: String,
    val date: Date,
    val category: Category = Category.None,
    val color: Color = Color.Unspecified
)