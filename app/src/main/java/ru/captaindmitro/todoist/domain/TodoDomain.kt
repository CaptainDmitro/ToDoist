package ru.captaindmitro.todoist.domain

import java.sql.Date

data class TodoDomain(
    val id: Int,
    val title: String,
    val body: String,
    val date: Date
)