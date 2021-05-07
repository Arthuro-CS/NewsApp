package com.cueva.newsapp.application.framework.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cueva.newsapp.application.framework.entity.NewsEntity

@Database(version = 1, entities = [NewsEntity::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNewsModelDao(): NewsDao

    companion object {

        val NEWS_DB = "news.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, NEWS_DB)
                .build()
    }

}