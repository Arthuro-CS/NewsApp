package com.cueva.newsapp.domain.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
    fun formatDate(dateTime: String?) : String{

        dateTime?.let {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            try {
                val inputDate = format.parse(it)
                val currentDate = Date()
                val diff = inputDate.time - currentDate.time
                val seconds = diff / 1000
                val minutes = seconds / 60
                val hours = minutes / 60
                val days = hours / 24

                return getTextFormatted(seconds,minutes,hours,days)

            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }
        }

        return ""
    }

    private fun getTextFormatted(seconds : Long,minutes: Long,hours: Long, days: Long) : String {
        val textFormatted : String
        if (days > 1)
            textFormatted = ""+days+" ago"
        else{
            if(hours >= 1)
                textFormatted = ""+hours+"h"
            else
                textFormatted = ""+minutes+"m"
        }
        return textFormatted
    }
}
}