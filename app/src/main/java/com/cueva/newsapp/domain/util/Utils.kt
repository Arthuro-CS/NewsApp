package com.cueva.newsapp.domain.util

import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import com.cueva.newsapp.domain.entity.Success
import java.lang.Exception
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {

        fun getNewsFormatted(
            news: ResultNews<List<News>, Exception>,
            listId: List<String>
        ): ResultNews<List<News>, Exception> {

            if (news is Success) {
                val mutableListNews = mutableListOf<News>()
                mutableListNews.addAll(news.value)
                mutableListNews
                    .removeAll { it.storyId in listId }
                return Success(mutableListNews
                    .sortedBy { Utils.getDiff(it.createAt) }
                    .map {
                        it.createAt = Utils.formatDate(it.createAt)
                        it
                    })
            } else
                return news


        }

        private fun getDiff(dateTime: String): Long {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            try {
                val inputDate = format.parse(dateTime)
                val currentDate = Date()
                return abs(inputDate.time - currentDate.time)
            } catch (e: Exception) {
                e.printStackTrace()
                return 0
            }
        }

        private fun formatDate(dateTime: String?): String {

            dateTime?.let {

                val diff = getDiff(it)
                val seconds = diff / 1000
                val minutes = seconds / 60
                val hours = minutes / 60
                val days = hours / 24

                return getTextFormatted(minutes, hours, days)
            }

            return ""
        }

        private fun getTextFormatted(
            minutes: Long,
            hours: Long,
            days: Long
        ): String {
            val textFormatted: String
            if (days >= 1)
                textFormatted = "" + days + " ago"
            else {
                if (hours >= 1)
                    textFormatted = "" + hours + "h"
                else
                    textFormatted = "" + minutes + "m"
            }
            return textFormatted
        }
    }
}