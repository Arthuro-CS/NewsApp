package com.cueva.newsapp.application.presentation.di

import android.app.Application
import androidx.room.Room
import com.cueva.newsapp.application.framework.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

    private var newsApplication = application
    private lateinit var appDatabase: AppDatabase


    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        appDatabase =
            Room.databaseBuilder(newsApplication, AppDatabase::class.java, AppDatabase.NEWS_DB)
                .fallbackToDestructiveMigration()
                .build()
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesNewsModelDao(appDatabase: AppDatabase) = appDatabase.getNewsModelDao()

}