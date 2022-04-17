package com.app.githubuser.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.githubuser.R
import com.app.githubuser.adapter.GitHubUserRepositoryAdapter
import com.app.githubuser.api.GitHubNetworkService
import com.app.githubuser.model.UserRepositoryInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_user_detailed_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val REPOSITORY_SIZE = 5
class UserDetailedInfoActivity : AppCompatActivity() {
    val TAG = javaClass.canonicalName
    val repositoryList : ArrayList<UserRepositoryInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detailed_info)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_github_user_repository_list.layoutManager = LinearLayoutManager(this)

        if (intent.hasExtra(Constants.LOGIN_ID)) {
            val loginId = intent.getStringExtra(Constants.LOGIN_ID)
            tv_user_login_id.text = loginId

            // Get repository API data
            if (loginId != null) {
                getRepositoriesOfUser(loginId)
            }
        }
        if (intent.hasExtra(Constants.AVATAR_URL)) {
            Glide.with(this)
                    .load(intent.getStringExtra(Constants.AVATAR_URL))
                    .apply(RequestOptions.circleCropTransform())
                    .error(R.drawable.profile)
                    .into(iv_user_profile)
        }

    }

    private fun getRepositoriesOfUser(loginId: String) {
        GitHubNetworkService.getApi().getUserRepositoryInfo(loginId).enqueue(object : Callback<List<UserRepositoryInfo>> {
            override fun onResponse(call: Call<List<UserRepositoryInfo>>, response: Response<List<UserRepositoryInfo>>) {
                if (response.isSuccessful && response.body() != null) {
                    for (i in 0 until REPOSITORY_SIZE) {
                        repositoryList.add(response.body()!![i])
                    }
                    //TODO: (next feature) add spinner while getting api
                    rv_github_user_repository_list.adapter = GitHubUserRepositoryAdapter(repositoryList, applicationContext)
                }
            }

            override fun onFailure(call: Call<List<UserRepositoryInfo>>, t: Throwable) {
                Log.e(TAG, "Failed to get GitHub User's Repos API!")
                Log.e(TAG, t.message.toString())
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}