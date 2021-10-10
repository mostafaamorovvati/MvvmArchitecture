package com.example.gitapp.data.remote.api

import com.example.gitapp.data.remote.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/list")
    suspend fun getUsers(
            @Query("page") index: Int,
            @Query("limit") count: Int
    ): Response<List<Photo>>

}