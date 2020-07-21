package com.example.githubuserapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.data.repository.MainRepository

class FollowingViewModel : ViewModel() {
    private val repository = MainRepository()
    private val userFollowing = MutableLiveData<List<Item>?>()
    val error = MutableLiveData<String>()

    fun getUserFollowing(username: String) {
        repository.userFollowing(username, {
            userFollowing.value = it
        }, {
            error.value = it.message
        })
    }

    fun getFollowing(): LiveData<List<Item>?> {
        return userFollowing
    }
}