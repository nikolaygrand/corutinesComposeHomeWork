package ru.mts.data.news.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.mts.data.news.db.NewsEntity
import ru.mts.data.news.db.NewsLocalDataSource
import ru.mts.data.news.remote.NewsRemoteDataSource
import ru.mts.data.utils.Result
import ru.mts.data.utils.mapSuccess

class NewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {
    suspend fun getNews(): Flow<Result<List<NewsEntity>, Throwable>> {
        return flow {
            newsLocalDataSource.getNews().mapSuccess {
                if (it.isEmpty()) {
                    emit(newsRemoteDataSource.getNews())
                } else {
                    emit(newsLocalDataSource.getNews())
                }
            }

        }
    }

    fun clearLocalDb() {
        newsLocalDataSource.clear()
    }

}
