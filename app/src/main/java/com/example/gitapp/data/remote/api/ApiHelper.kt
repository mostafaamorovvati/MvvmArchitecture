package com.example.gitapp.data.remote.api

import com.example.gitapp.data.remote.model.Photo
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<Photo>>

}