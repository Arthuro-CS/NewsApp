package com.cueva.newsapp.data.abstraction

import com.cueva.newsapp.domain.entity.News

interface ApiDataSource {
    suspend fun getNews() : List<News>
}