package com.cueva.newsapp.application.framework.retrofit

import com.cueva.newsapp.data.abstraction.ApiDataSource
import com.cueva.newsapp.domain.entity.News
import java.lang.Exception
import javax.inject.Inject

class NewsDataSource @Inject constructor(val newsService: NewsService) : ApiDataSource {
    override suspend fun getNews(): List<News> {
        try {
            return newsService.getNews().hits.map {
                it.mapToNews()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return listOf()
        }
    }
}