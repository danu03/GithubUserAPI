package com.example.githubuserapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapi.data.model.detail.ResponseUsersDetail
import com.example.githubuserapi.data.repository.MainRepository

class DetailViewModel : ViewModel() {
    private val repository = MainRepository()
    private val userDetail = MutableLiveData<ResponseUsersDetail>()
    private val error = MutableLiveData<String>()

    fun detail(username: String) {
        repository.detailUsers(username, {
            userDetail.value = it
        }, {
            error.value = it.message
        })
    }

    fun getDetail(): LiveData<ResponseUsersDetail> {
        return userDetail
    }
}