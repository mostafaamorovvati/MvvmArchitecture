package com.example.gitapp.di

import com.example.gitapp.data.remote.api.RickAndMortyApi
import com.example.gitapp.ui.characterList.CharacterAdapter
import org.koin.dsl.module

val appModule = module {

    factory { RickAndMortyApi() }
    factory { CharacterAdapter() }

}

