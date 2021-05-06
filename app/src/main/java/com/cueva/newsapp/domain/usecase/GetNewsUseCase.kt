package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsRepository
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.util.Utils
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository){

    suspend fun getNews():List<News>{
        return newsRepository.getNews().map {
            it.createAt = Utils.formatDate(it.createAt)
            it
        }
    }
}