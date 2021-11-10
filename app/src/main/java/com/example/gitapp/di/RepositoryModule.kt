package com.example.gitapp.di

import com.example.gitapp.data.repository.CharacterRepositoryImpl
import com.example.gitapp.data.repository.CharactersRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory <CharactersRepository>{
        CharacterRepositoryImpl(get())
    }
}