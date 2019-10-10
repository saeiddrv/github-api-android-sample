package ir.sdrv.mobilletsample.presentation.datasource

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import ir.sdrv.mobilletsample.data.remote.api.models.GithubUserModel
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.sdrv.mobilletsample.R

class UsersListAdapter(private val  listener: UsersListAdapterInteraction): PagedListAdapter<GithubUserModel, UsersListAdapter.UsersListViewHolder>(usersDiffCallback) {

    lateinit var context: Context

    interface UsersListAdapterInteraction {
        fun onUserItemClick(githubUserModel: GithubUserModel)
    }

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userItem: LinearLayout = itemView.findViewById(R.id.userItem)
        val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        val txtName: AppCompatTextView = itemView.findViewById(R.id.txtName)
        val txtHomeUrl: AppCompatTextView = itemView.findViewById(R.id.txtHomeUrl)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val githubUserModel = getItem(position)
        githubUserModel?.let {
            Glide.with(context)
                .load(it.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imgAvatar)

            holder.txtName.text = it.login
            holder.txtHomeUrl.text = it.htmlUrl

            holder.userItem.setOnClickListener {
                listener.onUserItemClick(githubUserModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_github_user, parent, false)
        return UsersListViewHolder(view)
    }

    companion object {
        val usersDiffCallback = object : DiffUtil.ItemCallback<GithubUserModel>() {
            override fun areItemsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}