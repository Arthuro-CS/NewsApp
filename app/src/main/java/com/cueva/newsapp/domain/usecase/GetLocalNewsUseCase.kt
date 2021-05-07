package com.cueva.newsapp.domain.usecase

import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import com.cueva.newsapp.domain.util.Utils
import java.lang.Exception
import javax.inject.Inject

class GetLocalNewsUseCase @Inject constructor(private val newsLocalRepository: NewsLocalRepository) {

    suspend fun getAllNews(): ResultNews<List<News>, Exception> {
        return Utils.getNewsFormatted(
            newsLocalRepository.getAllNews(),
            newsLocalRepository.getDeletedNews()
        )
    }
}