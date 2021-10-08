package com.example.gitapp.data.repository

import com.example.gitapp.data.remote.GithubApi

class UserRepository(private val api: GithubApi) {
    fun getAllUsers() = api.getUsers()
}