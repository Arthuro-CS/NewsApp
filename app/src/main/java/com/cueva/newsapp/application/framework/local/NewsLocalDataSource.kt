package com.cueva.newsapp.application.framework.local

import com.cueva.newsapp.application.framework.entity.NewsEntity
import com.cueva.newsapp.data.abstraction.LocalDataSource
import com.cueva.newsapp.domain.entity.News
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(val newsDao: NewsDao) : LocalDataSource {
    override suspend fun insertAllNews(news: List<News>) {
        newsDao.insertAll(news.map {
            NewsEntity(it.storyId, it.createAt, it.storyTitle, it.storyUrl, it.author)
        })
    }

    override suspend fun getAllNews(): List<News> {
        return newsDao.getAllNewsModel().map {
            it.toNews()
        }
    }

    override suspend fun clearAllNews() {
        newsDao.clearAllNews()
    }

}