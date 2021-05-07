package com.cueva.newsapp.application.presentation.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule(val context: Context) {
    @Provides
    fun provideSharedPrefernces(): SharedPreferences =
        context.getSharedPreferences("DATA", Context.MODE_PRIVATE)
}