package ru.mts.data.news.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mts.data.news.repository.News

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "full_description") val full_description: String?,
    @ColumnInfo(name = "pubDate") val pubDate: String
)

fun NewsEntity.toDomain() = with(this) {
    News(
        id,
        title,
        description,
        link,
        full_description,
        pubDate
    )
}