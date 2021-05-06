package com.cueva.newsapp.domain.abstraction

import com.cueva.newsapp.domain.entity.News

interface NewsLocalRepository {
    suspend fun insertAllNews(news: List<News>)
    suspend fun getAllNews(): List<News>
    suspend fun clearAllNews()
}