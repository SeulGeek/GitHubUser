package com.app.githubuser.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubNetworkService {
    private const val BASE_RUL = "https://api.github.com"

    fun getApi() : GitHubService = Retrofit.Builder()
            .baseUrl(BASE_RUL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)

}