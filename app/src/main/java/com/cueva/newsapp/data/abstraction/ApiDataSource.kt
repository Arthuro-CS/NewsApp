package com.cueva.newsapp.data.abstraction

import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import java.lang.Exception

interface ApiDataSource {
    suspend fun getNews(): ResultNews<List<News>, Exception>
}