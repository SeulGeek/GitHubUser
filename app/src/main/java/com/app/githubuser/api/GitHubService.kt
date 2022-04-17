package com.app.githubuser.api

import com.app.githubuser.model.GitHubUserList
import com.app.githubuser.model.GitHubUserInfo
import com.app.githubuser.model.UserRepositoryInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("search/users?q=repos:>1")
    fun getUserList(@Query("page") page : Int, @Query("per_page") pageSize: Int) : Call<GitHubUserList>

    @GET("users/{name}")
    fun getUserInfo(@Path("name") name : String) : Call<GitHubUserInfo>

    @GET("users/{name}/repos")
    fun getUserRepositoryInfo(@Path("name") name : String) : Call<List<UserRepositoryInfo>>

}