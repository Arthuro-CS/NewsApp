package com.cueva.newsapp.application.presentation.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.usecase.GetLocalNewsUseCase
import com.cueva.newsapp.domain.usecase.GetNewsUseCase
import com.cueva.newsapp.domain.usecase.InsertLocalNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    val getNewsUseCase: GetNewsUseCase,
    val getLocalNewsUseCase: GetLocalNewsUseCase
) : ViewModel() {

    private var _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = _newsList

    fun getNewsList(hasInternet: Boolean) {
        if (hasInternet)
            getNews()
        else
            getFromLocal()
    }

    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val news = getNewsUseCase.getNews()
            _newsList.postValue(news)
        }
    }

    private fun getFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.postValue(getLocalNewsUseCase.getAllNews())
        }
    }
}