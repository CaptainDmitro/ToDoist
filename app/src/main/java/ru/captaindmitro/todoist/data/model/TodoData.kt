package ru.captaindmitro.todoist.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.captaindmitro.todoist.domain.models.Category
import ru.captaindmitro.todoist.domain.models.TodoDomain
import java.sql.Date

@Entity
data class TodoData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val body: String,
    @ColumnInfo val timestamp: Long,
    @ColumnInfo val category: Category,
    @ColumnInfo val color: Int,
)

fun TodoData.toDomain(): TodoDomain = TodoDomain(
    id = this.id,
    title = this.title,
    body = this.body,
    date = Date(this.timestamp),
    category = this.category,
    color = Color(this.color)
)

fun TodoDomain.toData(): TodoData = TodoData(
    id = this.id,
    title = this.title,
    body = this.body,
    timestamp = this.date.time,
    category = this.category,
    color = this.color.toArgb()
)
