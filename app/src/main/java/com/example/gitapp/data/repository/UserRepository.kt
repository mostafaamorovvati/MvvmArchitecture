package com.example.gitapp.data.repository

import com.example.gitapp.data.remote.api.ApiHelper
import com.example.gitapp.data.remote.model.Photo
import retrofit2.Response

class UserRepository(private val apiHelper: ApiHelper) {

    suspend fun getAllUsers(page: Int, limit: Int): Response<ArrayList<Photo>> {
        return apiHelper.getUsers(page, limit)
    }

}