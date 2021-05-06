package com.cueva.newsapp.application.presentation.di

import com.cueva.newsapp.application.framework.local.NewsLocalDataSource
import com.cueva.newsapp.application.framework.retrofit.NewsDataSource
import com.cueva.newsapp.data.abstraction.ApiDataSource
import com.cueva.newsapp.data.abstraction.LocalDataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun providesDataSource(newsDataSource: NewsDataSource): ApiDataSource {
        return newsDataSource
    }

    @Provides
    fun providesLocalDataSource(newsLocalDataSource: NewsLocalDataSource): LocalDataSource {
        return newsLocalDataSource
    }
}