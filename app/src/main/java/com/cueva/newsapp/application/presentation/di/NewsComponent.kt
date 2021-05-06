package com.cueva.newsapp.application.presentation.di

import com.cueva.newsapp.application.presentation.ui.news.list.NewsListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsRepositoryModule::class,DataSourceModule::class,NetworkModule::class])
interface NewsComponent {
    fun inject(newsListFragment: NewsListFragment)
}