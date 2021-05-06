package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsRepository
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.util.Utils
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val insertLocalNewsUseCase: InsertLocalNewsUseCase,
    private val deleteAllLocalNews: DeleteAllLocalNews
) {

    suspend fun getNews(): List<News> {
        val news = newsRepository.getNews()
        deleteAllLocalNews.clearAllNews()
        insertLocalNewsUseCase.insertAllNews(news) //Insert news with complete date
        return Utils.getNewsFormatted(news)
    }
}