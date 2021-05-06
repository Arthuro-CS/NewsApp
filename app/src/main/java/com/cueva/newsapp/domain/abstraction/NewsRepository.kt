package com.cueva.newsapp.domain.abstraction

import com.cueva.newsapp.domain.entity.News

interface NewsRepository {
    suspend fun getNews(): List<News>
}