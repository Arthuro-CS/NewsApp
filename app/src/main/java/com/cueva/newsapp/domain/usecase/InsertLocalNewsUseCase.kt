package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import com.cueva.newsapp.domain.entity.News
import javax.inject.Inject

class InsertLocalNewsUseCase @Inject constructor(private val newsLocalRepository: NewsLocalRepository) {

    suspend fun insertAllNews(news: List<News>) {
        return newsLocalRepository.insertAllNews(news)
    }
}