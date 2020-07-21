package com.example.githubuserapi.data.api

import com.example.githubuserapi.data.model.detail.ResponseUsersDetail
import com.example.githubuserapi.data.model.search.Item
import com.example.githubuserapi.data.model.search.ResponseUserSearchGithub
import com.example.githubuserapi.utils.DETAILS
import com.example.githubuserapi.utils.FOLLOWERS
import com.example.githubuserapi.utils.FOLLOWING
import com.example.githubuserapi.utils.SEARCH
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(SEARCH)
    fun searchUsers(
        @Header("Authorization") token: String,
        @Query("q") username: String
    ) : Observable<ResponseUserSearchGithub>

    @GET(DETAILS)
    fun detailUsers(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ) : Observable<ResponseUsersDetail>

    @GET(FOLLOWERS)
    fun getUserFollowers(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ) : Observable<List<Item>>

    @GET(FOLLOWING)
    fun getUserFollowing(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ) : Observable<List<Item>>
}