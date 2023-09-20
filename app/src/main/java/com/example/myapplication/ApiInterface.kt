package com.example.myapplication

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/todos")
    suspend fun getTodoList(): Response<List<Todo>>

    @GET("/api/users")
    suspend fun getAllUserByPage(@Query("page") page: Int,@Query("per_page") pagPer:Int): Response<Support>

    @GET("/api/users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<UserResponse>

    @POST("/api/users")
    suspend fun createUser(@Body userCreate: UserCreate): Response<UserCreate>

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id") idUser:Int):Response<Unit>




}