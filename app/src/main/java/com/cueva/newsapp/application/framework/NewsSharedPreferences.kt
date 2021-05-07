package com.cueva.newsapp.application.framework

import android.content.SharedPreferences
import javax.inject.Inject

class NewsSharedPreferences @Inject constructor(val sharedPreferences: SharedPreferences) {

    companion object {
        val NEWS_KEY = "MyNewsDeleted"
    }

    fun putData(key: String, data: Set<String>) =
        sharedPreferences.edit().putStringSet(key, data).apply()

    fun getData(key: String): Set<String>? = sharedPreferences.getStringSet(key, null)
}