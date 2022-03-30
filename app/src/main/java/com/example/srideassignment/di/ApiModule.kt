package com.example.srideassignment.di

import com.example.srideassignment.api.ApiInterface
import com.example.srideassignment.repositiory.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideBaseUrl(): String{
        return "https://newsapi.org/v2/"
    }

    @Provides
    fun getRetroFitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClients())
            .build()
    }

    @Provides
    fun httpLogingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    }

    @Provides
    fun provideOkHttpClients(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(httpLogingInterceptor())
            .readTimeout(45, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(45, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideApiInterFace(): ApiInterface {
        return getRetroFitInstance().create(ApiInterface::class.java)
    }

    @Provides
    fun provideNewsRepository(apiInterface: ApiInterface): NewsRepo {
        return NewsRepo(apiInterface)
    }
}