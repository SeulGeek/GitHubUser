package com.app.githubuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.githubuser.R
import com.app.githubuser.model.UserRepositoryInfo
import kotlinx.android.synthetic.main.item_repository_list.view.*

class GitHubUserRepositoryAdapter(val items: ArrayList<UserRepositoryInfo>, val context: Context) : RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repository_list, parent, false))
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = items[position]
        holder.tvProjectName.text = repository.name
        holder.tvUpdatedDate.text = repository.updateDate
        holder.tvProjectDesc.text = repository.description

        holder.layoutRepository.setOnClickListener{
            //TODO: (next feature) move to repository link using html_url
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val layoutRepository = view.layout_repository_item
    val tvProjectName = view.tv_project_name
    val tvUpdatedDate = view.tv_updated_date
    val tvProjectDesc = view.tv_project_desc

}