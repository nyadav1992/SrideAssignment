package com.example.srideassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.srideassignment.api.Response
import com.example.srideassignment.data.NewsDataResponse
import com.example.srideassignment.repositiory.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val repo: NewsRepo) : ViewModel() {

val newsData: LiveData<Response<NewsDataResponse>> = repo.newsData

    fun getNewsDataList() {repo.getNewsListFromApi()}

}