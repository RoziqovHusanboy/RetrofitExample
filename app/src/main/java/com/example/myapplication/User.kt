package com.example.myapplication

data class User(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
){
    fun getFullname() = "$first_name $last_name"
}