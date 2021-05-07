package com.cueva.newsapp.application.presentation.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.entity.ResultNews
import com.cueva.newsapp.domain.entity.Success
import com.cueva.newsapp.domain.usecase.DeleteNewsUseCase
import com.cueva.newsapp.domain.usecase.GetLocalNewsUseCase
import com.cueva.newsapp.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    val getNewsUseCase: GetNewsUseCase,
    val getLocalNewsUseCase: GetLocalNewsUseCase,
    val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModel() {

    private var _newsList = MutableLiveData<ResultNews<List<News>, Exception>>()
    val newsList: LiveData<ResultNews<List<News>, Exception>> get() = _newsList

    fun getNewsList(hasInternet: Boolean) {
        if (hasInternet)
            getNews()
        else
            getFromLocal()
    }

    fun deleteNews(position: Int) {
        val result =
            newsList.value
        result?.let {
            if (it is Success) {
                val mutableList = it.value.toMutableList()
                mutableList.remove(it.value.get(position))
                _newsList.postValue(Success(mutableList))
            }
        }
        viewModelScope.launch {
            val result =
                newsList.value
            if (result is Success)
                deleteNewsUseCase.deleteNews(result.value.get(position)?.storyId ?: "")
        }
    }

    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.postValue(getNewsUseCase.getNews())
        }
    }

    private fun getFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.postValue(getLocalNewsUseCase.getAllNews())
        }
    }
}