package com.example.githubuserapi.data.model.search


import com.google.gson.annotations.SerializedName

data class ResponseUserSearchGithub(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)