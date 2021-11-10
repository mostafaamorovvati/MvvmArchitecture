package com.example.gitapp.data.repository

import com.apollographql.apollo.api.Response
import com.example.apollographql.CharactersListQuery

interface CharactersRepository {

    suspend fun queryCharactersList(): Response<CharactersListQuery.Data>

}