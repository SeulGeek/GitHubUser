package com.app.githubuser.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRepositoryInfo(
    @SerializedName("name") var name : String,
    @SerializedName("html_url") var htmlUrl : String,
    @SerializedName("updated_at") var updateDate : String,
    @SerializedName("description") var description : String
) : Serializable