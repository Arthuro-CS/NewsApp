package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(private val newsLocalRepository: NewsLocalRepository) {
    suspend fun deleteNews(idNews: String) {
        if (idNews == "")
            return
        newsLocalRepository.deleteNews(idNews)
    }
}