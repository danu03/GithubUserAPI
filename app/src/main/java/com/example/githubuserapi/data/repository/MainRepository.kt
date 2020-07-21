package com.example.githubuserapi.data.repository

import com.example.githubuserapi.data.model.detail.ResponseUsersDetail
import com.example.githubuserapi.data.model.search.ResponseUserSearchGithub
import com.example.githubuserapi.data.api.ApiResource
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.utils.TOKEN
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainRepository {
    private val token = TOKEN
    fun searchUser(
        username: String,
        onResult: (ResponseUserSearchGithub) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        ApiResource.created().searchUsers(token, username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseUserSearchGithub> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: ResponseUserSearchGithub) {
                    onResult(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }

    fun detailUsers(
        username: String,
        onResult: (ResponseUsersDetail) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        ApiResource.created().detailUsers(token, username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseUsersDetail> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: ResponseUsersDetail) {
                    onResult(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }

            })
    }

    fun userFollower(
        username: String,
        onResult: (List<Item>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        ApiResource.created().getUserFollowers(token, username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Item>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<Item>) {
                    onResult(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }

    fun userFollowing(
        username: String,
        onResult: (List<Item>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        ApiResource.created().getUserFollowing(token, username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Item>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<Item>) {
                    onResult(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }


}