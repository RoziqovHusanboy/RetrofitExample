package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Support(
    @SerializedName("data")
    val userList: List<User>,
    val page: Int,
    val per_page: Int,
    val support: SupportX,
    val total: Int,
    val total_pages: Int
)