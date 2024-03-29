package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import com.cueva.newsapp.domain.abstraction.NewsRepository
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import com.cueva.newsapp.domain.entity.Success
import com.cueva.newsapp.domain.util.Utils
import java.lang.Exception
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val insertLocalNewsUseCase: InsertLocalNewsUseCase,
    private val deleteAllLocalNews: DeleteAllLocalNews,
    private val newsLocalRepository: NewsLocalRepository
) {

    suspend fun getNews(): ResultNews<List<News>, Exception> {
        val news = newsRepository.getNews()
        deleteAllLocalNews.clearAllNews()
        if (news is Success)
            insertLocalNewsUseCase.insertAllNews(news.value) //Insert news with complete date
        return Utils.getNewsFormatted(news, newsLocalRepository.getDeletedNews())
    }
}