package ru.captaindmitro.todoist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.captaindmitro.todoist.domain.TodoDomain
import java.sql.Date

@Entity
data class TodoData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val body: String,
    @ColumnInfo val timestamp: Long
)

fun TodoData.toDomain(): TodoDomain = TodoDomain(
    id = this.id,
    title = this.title,
    body = this.body,
    date = Date(this.timestamp)
)

fun TodoDomain.toData(): TodoData = TodoData(
    id = this.id,
    title = this.title,
    body = this.body,
    timestamp = this.date.time
)
