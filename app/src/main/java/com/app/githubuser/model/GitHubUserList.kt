package com.app.githubuser.model

import com.google.gson.annotations.SerializedName
import retrofit2.Call

data class GitHubUserList(
    @SerializedName("items") var items : List<GitHubUserInfo>
)