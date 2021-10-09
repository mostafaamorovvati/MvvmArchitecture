package com.example.gitapp.di

import com.example.gitapp.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        UserRepository(get())
    }

}