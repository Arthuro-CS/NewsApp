package com.cueva.newsapp.data.repository

import com.cueva.newsapp.data.abstraction.LocalDataSource
import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import java.lang.Exception
import javax.inject.Inject

class NewsLocalRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    NewsLocalRepository {
    override suspend fun insertAllNews(news: List<News>) {
        return localDataSource.insertAllNews(news)
    }

    override suspend fun getAllNews(): ResultNews<List<News>, Exception> {
        return localDataSource.getAllNews()
    }

    override suspend fun clearAllNews() {
        localDataSource.clearAllNews()
    }

    override suspend fun getDeletedNews(): List<String> {
        return localDataSource.getDeletedNews()
    }

    override suspend fun deleteNews(idNews: String) {
        localDataSource.deleteNews(idNews)
    }
}