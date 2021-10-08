package com.example.gitapp.data.remote

import com.example.gitapp.data.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<GithubUser>>
}