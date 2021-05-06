package com.cueva.newsapp.application.presentation.di

import com.cueva.newsapp.data.repository.NewsRepositoryImpl
import com.cueva.newsapp.domain.abstraction.NewsRepository
import dagger.Module
import dagger.Provides

@Module
class NewsRepositoryModule {
    @Provides
    fun providesLeagueRepositoryImpl(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository{
        return newsRepositoryImpl
    }
}