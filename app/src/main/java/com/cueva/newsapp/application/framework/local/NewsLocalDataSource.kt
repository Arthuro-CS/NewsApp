package com.cueva.newsapp.application.framework.local

import com.cueva.newsapp.application.framework.NewsSharedPreferences
import com.cueva.newsapp.application.framework.entity.NewsEntity
import com.cueva.newsapp.data.abstraction.LocalDataSource
import com.cueva.newsapp.domain.entity.Failure
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import com.cueva.newsapp.domain.entity.Success
import java.lang.Exception
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    val newsDao: NewsDao,
    val newsSharedPreferences: NewsSharedPreferences
) : LocalDataSource {
    override suspend fun insertAllNews(news: List<News>) {
        newsDao.insertAll(news.map {
            NewsEntity(it.storyId, it.createAt, it.storyTitle, it.storyUrl, it.author)
        })
    }

    override suspend fun getAllNews(): ResultNews<List<News>, Exception> {
        try {
            return Success(newsDao.getAllNewsModel().map {
                it.toNews()
            })
        } catch (e: Exception) {
            return Failure(e)
        }
    }

    override suspend fun clearAllNews() {
        newsDao.clearAllNews()
    }

    override suspend fun getDeletedNews(): List<String> {
        return newsSharedPreferences.getData(NewsSharedPreferences.NEWS_KEY)?.toList() ?: listOf()
    }

    override suspend fun deleteNews(idNews: String) {
        val listNews: List<String>? =
            newsSharedPreferences.getData(NewsSharedPreferences.NEWS_KEY)?.toList()
        val newListNews = mutableListOf(idNews)
        if (listNews != null) {
            newListNews.addAll(listNews)
        }
        newsSharedPreferences.putData(NewsSharedPreferences.NEWS_KEY, newListNews.toSet())
        newsDao.deleteByNewsId(idNews)
    }

}