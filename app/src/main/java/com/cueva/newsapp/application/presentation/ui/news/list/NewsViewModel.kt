package com.cueva.newsapp.application.presentation.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cueva.newsapp.domain.entity.News
import com.cueva.newsapp.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(val getNewsUseCase: GetNewsUseCase) : ViewModel(){

    private var _newsList = MutableLiveData<List<News>>()
    val newsList : LiveData<List<News>> get() = _newsList

    fun getNews(){
        viewModelScope.launch {
            _newsList.value = getNewsUseCase.getNews()
        }
    }

}