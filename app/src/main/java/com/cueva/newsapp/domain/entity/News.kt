package com.cueva.newsapp.domain.entity

data class News(
    val storyId: String,
    var createAt: String,
    val storyTitle: String,
    val storyUrl: String,
    val author: String?,
) {
}