package com.example.srideassignment.api

import com.example.srideassignment.data.NewsDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("everything")
    suspend fun getNewsList(@Query("q") q: String, @Query("apiKey") apiKey: String) : Response<NewsDataResponse>
}