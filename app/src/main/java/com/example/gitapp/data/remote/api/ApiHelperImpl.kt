package com.example.gitapp.data.remote.api

import com.example.gitapp.data.remote.model.Photo
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(page: Int, limit: Int): Response<ArrayList<Photo>> {
        return apiService.getUsers(page, limit)
    }
}