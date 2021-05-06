package com.cueva.newsapp.application.framework.retrofit

import com.cueva.newsapp.data.abstraction.ApiDataSource
import com.cueva.newsapp.domain.entity.News
import javax.inject.Inject

class NewsDataSource @Inject constructor(val newsService: NewsService) : ApiDataSource {
    override suspend fun getNews(): List<News> {
        return newsService.getNews().hits.map {
            it.mapToNews()
        }
    }
}