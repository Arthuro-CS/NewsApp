package com.cueva.newsapp.domain.abstraction

import com.cueva.newsapp.domain.entity.News

interface NewsLocalRepository {
    suspend fun insertAllNews(news: List<News>)
    suspend fun getAllNews(): List<News>
    suspend fun clearAllNews()
    suspend fun getDeletedNews() : List<String>
    suspend fun deleteNews(idNews : String)
}