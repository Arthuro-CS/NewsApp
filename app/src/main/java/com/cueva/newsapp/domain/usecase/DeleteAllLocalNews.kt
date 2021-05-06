package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import javax.inject.Inject

class DeleteAllLocalNews @Inject constructor(private val newsLocalRepository: NewsLocalRepository) {

    suspend fun clearAllNews() {
        newsLocalRepository.clearAllNews()
    }
}