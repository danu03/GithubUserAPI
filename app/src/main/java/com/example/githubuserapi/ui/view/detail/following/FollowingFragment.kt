package com.example.githubuserapi.ui.view.detail.following

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapi.R
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.data.pref.PreferenceHelper
import com.example.githubuserapi.ui.adapter.FollowingAdapter
import com.example.githubuserapi.ui.viewmodel.FollowingViewModel
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {
    private val listFollowing: MutableList<Item> = mutableListOf()
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var followingViewModel: FollowingViewModel
    private lateinit var followingAdapter: FollowingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java
        )

        followingAdapter = FollowingAdapter(listFollowing)
        rv_following.layoutManager = LinearLayoutManager(activity)
        rv_following.adapter = followingAdapter

        preferenceHelper = PreferenceHelper(requireContext())

        val username = preferenceHelper.username.toString()
        followingViewModel.getUserFollowing(username)
        showLoading(true)

        followingViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            Log.d("ErrorFollowing", it)
        })

        followingViewModel.getFollowing().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                listFollowing.clear()
                listFollowing.addAll(it)
                followingAdapter.notifyDataSetChanged()
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_following.visibility = View.VISIBLE
        } else {
            pb_following.visibility = View.GONE
        }
    }
}