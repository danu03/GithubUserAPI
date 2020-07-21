package com.example.githubuserapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapi.R
import com.example.githubuserapi.data.model.search.Item
import kotlinx.android.synthetic.main.item_user_github.view.*

class UserAdapter(private val userData : MutableList<Item>) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    private lateinit var listener: OnClickCallback

    fun setOnItemClickCallback(onClickCallback: OnClickCallback) {
        this.listener = onClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_github, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userData[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, position: Int){
            Glide.with(itemView).load(item.avatarUrl).into(itemView.iv_photo)
            itemView.tv_name.text = item.login
            itemView.setOnClickListener {
                listener.onItemClicked(item, position)
            }
        }
    }

    interface OnClickCallback {
        fun onItemClicked(data: Item, position: Int)
    }
}