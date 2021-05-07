package com.cueva.newsapp.application.framework.entity

import com.cueva.newsapp.domain.entity.News
import com.squareup.moshi.Json

class NewsResponse(
    val hits: List<NewsItem>
) {
}


class NewsItem(
    @Json(name = "objectID")
    val storyId: String,
    @Json(name = "created_at")
    val createAt: String?,
    @Json(name = "story_title")
    val storyTitle: String?,
    @Json(name = "story_url")
    val storyUrl: String?,
    @Json(name = "author")
    val author: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?
) {
    fun mapToNews(): News {
        return News(
            storyId,
            createAt ?: "",
            storyTitle ?: title ?: "No Title Available",
            storyUrl ?: url ?: "",
            author ?: ""
        )
    }
}