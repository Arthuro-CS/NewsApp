package com.cueva.newsapp.application.framework.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cueva.newsapp.domain.entity.News

@Entity(tableName = "news")
class NewsEntity(
    @PrimaryKey
    val storyId: String,
    var createAt: String,
    val storyTitle: String,
    val storyUrl: String,
    val author: String?,
) {
    fun toNews(): News {
        return News(storyId, createAt, storyTitle, storyUrl, author)
    }
}