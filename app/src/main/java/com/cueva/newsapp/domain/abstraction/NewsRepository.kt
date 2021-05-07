package com.cueva.newsapp.domain.abstraction

import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import java.lang.Exception

interface NewsRepository {
    suspend fun getNews(): ResultNews<List<News>, Exception>
}