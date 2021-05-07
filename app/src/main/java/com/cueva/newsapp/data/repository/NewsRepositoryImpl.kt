package com.cueva.newsapp.data.repository

import com.cueva.newsapp.data.abstraction.ApiDataSource
import com.cueva.newsapp.domain.abstraction.NewsRepository
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiDataSource: ApiDataSource) :
    NewsRepository {
    override suspend fun getNews(): ResultNews<List<News>, Exception> {
        return apiDataSource.getNews()
    }
}