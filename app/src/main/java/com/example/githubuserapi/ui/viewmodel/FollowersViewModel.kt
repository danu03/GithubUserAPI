package com.example.githubuserapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.data.repository.MainRepository

class FollowersViewModel : ViewModel() {
    private val repository = MainRepository()
    private val userFollowers = MutableLiveData<List<Item>?>()
    val error = MutableLiveData<String>()

    fun getUserFollowers(username: String) {
        repository.userFollower(username, {
            userFollowers.value = it
        }, {
            error.value = it.message
        })
    }

    fun getFollowers(): LiveData<List<Item>?> {
        return userFollowers
    }
}