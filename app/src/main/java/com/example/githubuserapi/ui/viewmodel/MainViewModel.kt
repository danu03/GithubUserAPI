package com.example.githubuserapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.data.repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository()
    private val user = MutableLiveData<List<Item>?>()
    val error = MutableLiveData<String>()

    fun searchUser(username: String) {
        repository.searchUser(username, {
            user.value = it.items
        }, {
            error.value = it.message
        })
    }

    fun getUser(): LiveData<List<Item>?> {
        return user
    }
}