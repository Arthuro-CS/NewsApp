package com.cueva.newsapp.application.presentation.di

import android.app.Application
import com.cueva.newsapp.application.presentation.ui.news.list.NewsListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class, NewsRepositoryModule::class, DataSourceModule::class, NetworkModule::class, RoomModule::class])
interface NewsComponent {
    fun inject(newsListFragment: NewsListFragment)
}