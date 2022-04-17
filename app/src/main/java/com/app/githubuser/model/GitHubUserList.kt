package com.app.githubuser.model

import com.google.gson.annotations.SerializedName

data class GitHubUserList(
    @SerializedName("items") var items : List<GitHubUserInfo>
)