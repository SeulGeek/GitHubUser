package com.app.githubuser.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.githubuser.R
import com.app.githubuser.adapter.GitHubUserAdapter
import com.app.githubuser.api.GitHubNetworkService
import com.app.githubuser.model.GitHubUserInfo
import com.app.githubuser.model.GitHubUserList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val PAGE_SIZE = 10

class MainActivity : AppCompatActivity() {

    val TAG = javaClass.canonicalName
    val userList : ArrayList<GitHubUserInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_github_user_list.layoutManager = LinearLayoutManager(this)
        setUserList()
    }

    private fun setUserList() {
        GitHubNetworkService.getApi().getUserList(1, PAGE_SIZE).enqueue(object : Callback<GitHubUserList> {
            override fun onResponse(call: Call<GitHubUserList>, response: Response<GitHubUserList>) {
                if (response.isSuccessful && response.body() != null) {
                    for (i in 0 until PAGE_SIZE) {
                        userList.add(response.body()!!.items[i])
                    }
                    rv_github_user_list.adapter = GitHubUserAdapter(userList,applicationContext)
                }
            }

            override fun onFailure(call: Call<GitHubUserList>, t: Throwable) {
                Log.e(TAG, "Failed to get GitHub User List API!")
            }
        })
    }
}