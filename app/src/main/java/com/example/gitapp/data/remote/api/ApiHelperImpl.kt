package com.example.gitapp.data.remote.api

import com.example.gitapp.data.remote.model.Photo
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<Photo>> {
        return apiService.getUsers()
    }
}