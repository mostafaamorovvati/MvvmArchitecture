package com.example.gitapp.data.remote.api

import com.example.gitapp.data.remote.model.Photo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/list")
    suspend fun getUsers(): Response<List<Photo>>

}