package com.cueva.newsapp.application.framework.retrofit

import com.cueva.newsapp.application.framework.entity.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("search_by_date")
    suspend fun getNews(@Query("query") query: String = "mobile") : NewsResponse
}