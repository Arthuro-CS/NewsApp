package com.cueva.newsapp.domain.abstraction

import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import java.lang.Exception

interface NewsLocalRepository {
    suspend fun insertAllNews(news: List<News>)
    suspend fun getAllNews(): ResultNews<List<News>, Exception>
    suspend fun clearAllNews()
    suspend fun getDeletedNews(): List<String>
    suspend fun deleteNews(idNews: String)
}