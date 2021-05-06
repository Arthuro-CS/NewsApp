package com.cueva.newsapp.data.repository

import com.cueva.newsapp.data.abstraction.ApiDataSource
import com.cueva.newsapp.domain.abstraction.NewsRepository
import com.cueva.newsapp.domain.entity.News
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiDataSource: ApiDataSource) : NewsRepository{
    override suspend fun getNews(): List<News> {
        return apiDataSource.getNews()
    }
}