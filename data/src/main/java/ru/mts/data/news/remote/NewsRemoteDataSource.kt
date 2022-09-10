package ru.mts.data.news.remote

import android.content.Context
import kotlinx.coroutines.delay
import ru.mts.data.main.AppDatabase
import ru.mts.data.main.NetworkClient
import ru.mts.data.news.db.NewsEntity
import ru.mts.data.utils.Result
import ru.mts.data.utils.runOperationCatching

class NewsRemoteDataSource(private val context: Context) {
    suspend fun getNews(): Result<List<NewsEntity>, Throwable> {
        return runOperationCatching {
            delay(3000L)
            NetworkClient.create()
                .getSampleData(NewsDto.Request()).map {
                    val item = it.toDomain()
                    AppDatabase.getDatabase(context).newsDao().insert(item)
                    item
                }
        }
    }
}