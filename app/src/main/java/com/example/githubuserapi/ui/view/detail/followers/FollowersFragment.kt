package com.example.githubuserapi.ui.view.detail.followers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapi.R
import com.example.githubuserapi.ui.adapter.FollowersAdapter
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.data.pref.PreferenceHelper
import com.example.githubuserapi.ui.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {
    private val listFollowers: MutableList<Item> = mutableListOf()
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var followersAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowersViewModel::class.java
        )

        followersAdapter = FollowersAdapter(listFollowers)
        rv_followers.layoutManager = LinearLayoutManager(activity)
        rv_followers.adapter = followersAdapter

        preferenceHelper = PreferenceHelper(requireContext())

        val username = preferenceHelper.username.toString()
        followersViewModel.getUserFollowers(username)
        showLoading(true)

        followersViewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            Log.d("ErrorFollowers", it)
        })

        followersViewModel.getFollowers().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                listFollowers.clear()
                listFollowers.addAll(it)
                followersAdapter.notifyDataSetChanged()
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_followers.visibility = View.VISIBLE
        } else {
            pb_followers.visibility = View.GONE
        }
    }

}