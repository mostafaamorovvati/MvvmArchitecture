package com.example.gitapp.data.repository

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.apollographql.CharactersListQuery
import com.example.gitapp.data.remote.api.RickAndMortyApi

class CharacterRepositoryImpl(
    private val webRickAndMorty: RickAndMortyApi
) : CharactersRepository {

    override suspend fun queryCharactersList(): Response<CharactersListQuery.Data> {
        return webRickAndMorty.getApolloClient().query(CharactersListQuery()).await()
    }
}