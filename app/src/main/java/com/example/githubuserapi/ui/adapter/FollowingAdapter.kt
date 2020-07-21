package com.example.githubuserapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapi.R
import com.example.githubuserapi.data.model.search.Item
import kotlinx.android.synthetic.main.item_following.view.*

class FollowingAdapter(private val userFollowing: MutableList<Item>) : RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_following, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userFollowing.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userFollowing[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.apply {
                Glide.with(itemView).load(item.avatarUrl).into(iv_photo_following)
                tv_username_following.text = item.login
            }
        }
    }

}