package com.app.githubuser.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.githubuser.R
import com.app.githubuser.model.GitHubUserInfo
import com.app.githubuser.view.Constants
import com.app.githubuser.view.UserDetailedInfoActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user_list.view.*

class GitHubUserAdapter(val items: ArrayList<GitHubUserInfo>, private val context: Context) : RecyclerView.Adapter<GitHubUserAdapter.UserViewHolder>() {

    val TAG = javaClass.canonicalName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val gitHubUser = items[position]

        holder.tvUserLoginId.text = gitHubUser.login
        Glide.with(context)
                .load(gitHubUser.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.profile)
                .into(holder.ivUserProfile)

        holder.layoutUserItem.setOnClickListener{
            goUserDetailedPage(gitHubUser.login, gitHubUser.avatarUrl)
        }
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    private fun goUserDetailedPage(loginId: String, avatarUrl: String) {
        val intent = Intent(context,UserDetailedInfoActivity::class.java)
        intent.putExtra(Constants.LOGIN_ID, loginId)
        intent.putExtra(Constants.AVATAR_URL, avatarUrl)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val layoutUserItem = view.layout_user_item
        val ivUserProfile = view.iv_user_profile
        val tvUserLoginId =  view.tv_user_login_id
        val vLine =  view.v_line
    }

}

