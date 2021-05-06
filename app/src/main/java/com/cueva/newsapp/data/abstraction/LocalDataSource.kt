package com.cueva.newsapp.data.abstraction

import com.cueva.newsapp.domain.entity.News

interface LocalDataSource {
    suspend fun insertAllNews(news: List<News>)
    suspend fun getAllNews(): List<News>
    suspend fun clearAllNews()
}