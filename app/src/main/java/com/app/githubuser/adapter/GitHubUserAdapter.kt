package com.app.githubuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.githubuser.R
import com.app.githubuser.model.GitHubUserInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user_list.view.*

class GitHubUserAdapter(val items: ArrayList<GitHubUserInfo>, val context: Context) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val gitHubUser = items[position]

        Glide.with(context)
            .load(gitHubUser.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.profile)
            .into(holder.ivUserProfile)

        holder.tvUserLoginId.text = gitHubUser.login

    }

    override fun getItemCount(): Int {
        return items.size;
    }
}

class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val ivUserProfile = view.iv_user_profile
    val tvUserLoginId =  view.tv_user_login_id
    val vLine =  view.v_line
}

