package com.cueva.newsapp.application.presentation.di

import com.cueva.newsapp.data.repository.NewsLocalRepositoryImpl
import com.cueva.newsapp.data.repository.NewsRepositoryImpl
import com.cueva.newsapp.domain.abstraction.NewsLocalRepository
import com.cueva.newsapp.domain.abstraction.NewsRepository
import dagger.Module
import dagger.Provides

@Module
class NewsRepositoryModule {
    @Provides
    fun providesNewsRepositoryImpl(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return newsRepositoryImpl
    }

    @Provides
    fun providesNewsLocalRepositoryImpl(newsLocalRepositoryImpl: NewsLocalRepositoryImpl): NewsLocalRepository {
        return newsLocalRepositoryImpl
    }
}