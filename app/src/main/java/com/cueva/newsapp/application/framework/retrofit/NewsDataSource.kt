package com.cueva.newsapp.application.framework.retrofit


import com.cueva.newsapp.data.abstraction.ApiDataSource
import com.cueva.newsapp.domain.entity.Failure
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import com.cueva.newsapp.domain.entity.Success
import java.lang.Exception
import javax.inject.Inject

class NewsDataSource @Inject constructor(val newsService: NewsService) : ApiDataSource {
    override suspend fun getNews(): ResultNews<List<News>, Exception> {
        try {
            return Success(newsService.getNews().hits.map {
                it.mapToNews()
            })
        } catch (e: Exception) {
            e.printStackTrace()
            return Failure(e)
        }
    }
}