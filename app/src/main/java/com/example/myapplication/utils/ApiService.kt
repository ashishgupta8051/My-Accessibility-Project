package com.example.myapplication.utils

import com.example.myapplication.model.Users
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getUsersList():Response<List<Users>>
}