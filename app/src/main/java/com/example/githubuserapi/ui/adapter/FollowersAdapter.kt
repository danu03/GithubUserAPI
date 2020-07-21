package com.example.githubuserapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapi.R
import com.example.githubuserapi.data.model.search.Item
import kotlinx.android.synthetic.main.item_followers.view.*

class FollowersAdapter(private val userFollowers : MutableList<Item>) : RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_followers, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userFollowers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userFollowers[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.apply {
                Glide.with(itemView).load(item.avatarUrl).into(iv_photo_followers)
                tv_username_followers.text = item.login
            }
        }
    }
}