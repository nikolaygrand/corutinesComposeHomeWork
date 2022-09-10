package ru.mts.data.news.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.mts.data.news.db.NewsLocalDataSource
import ru.mts.data.news.remote.NewsRemoteDataSource
import ru.mts.data.utils.Result

class NewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {
    suspend fun getNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            emit(newsRemoteDataSource.getNews())
        }
    }
    
}
