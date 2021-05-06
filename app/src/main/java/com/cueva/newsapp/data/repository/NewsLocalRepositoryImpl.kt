package com.cueva.newsapp.data.repository

import com.cueva.newsapp.data.abstraction.LocalDataSource
import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import com.cueva.newsapp.domain.entity.News
import javax.inject.Inject

class NewsLocalRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    NewsLocalRepository {
    override suspend fun insertAllNews(news: List<News>) {
        return localDataSource.insertAllNews(news)
    }

    override suspend fun getAllNews(): List<News> {
        return localDataSource.getAllNews()
    }

    override suspend fun clearAllNews() {
        localDataSource.clearAllNews()
    }
}