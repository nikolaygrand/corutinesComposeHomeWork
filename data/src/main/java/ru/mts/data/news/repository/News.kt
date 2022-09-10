package ru.mts.data.news.repository

data class News(
    val id: Int,
    val title: String,
    val description: String?,
    val link: String,
    val full_description: String?,
    val pubDate: String
)
