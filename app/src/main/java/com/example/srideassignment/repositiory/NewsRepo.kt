package com.example.srideassignment.repositiory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.srideassignment.api.ApiInterface
import com.example.srideassignment.api.Response
import com.example.srideassignment.data.NewsDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NewsRepo (private val apiInterface: ApiInterface) {

    fun getNewsListFromApi() {
        MainScope().launch(Dispatchers.IO) {
            val response = apiInterface.getNewsList("tesla", "1c531a761baf475ba3f9a6a5e3c3ec06")
            if (response.body() != null)
                newsLiveData.postValue(Response.Success(response.body()!!))
            else
                newsLiveData.postValue(Response.Error(response.message() + " " + response.code()))
        }
    }

    private var newsLiveData = MutableLiveData<Response<NewsDataResponse>>()

    val newsData: LiveData<Response<NewsDataResponse>>
        get() {
            return newsLiveData
        }


}