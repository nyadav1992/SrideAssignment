package com.example.srideassignment.data

data class NewsDataResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)