package com.example.myapplication.utils

import retrofit2.Response

interface ApiProcess<T> {

    fun showLoader(loader:Boolean)

    suspend fun sendRequest(apiService: ApiService): Response<T>

    fun success(response: Response<Any>)

    fun failure(message:String)
}