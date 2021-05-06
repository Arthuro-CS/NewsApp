package com.cueva.newsapp.application.framework.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cueva.newsapp.application.framework.entity.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<NewsEntity>)

    @Query("SELECT * FROM news")
    fun getAllNewsModel(): List<NewsEntity>

    @Query("DELETE FROM news")
    suspend fun clearAllNews()
}