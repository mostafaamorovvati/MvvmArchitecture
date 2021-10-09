package com.example.gitapp.data.remote.api

import com.example.gitapp.data.remote.model.GithubUser
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<GithubUser>>

}