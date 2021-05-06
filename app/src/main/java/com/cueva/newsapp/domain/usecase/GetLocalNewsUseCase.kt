package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.util.Utils
import javax.inject.Inject

class GetLocalNewsUseCase @Inject constructor(private val newsLocalRepository: NewsLocalRepository) {

    suspend fun getAllNews(): List<News> {
        return Utils.getNewsFormatted(newsLocalRepository.getAllNews())
    }
}