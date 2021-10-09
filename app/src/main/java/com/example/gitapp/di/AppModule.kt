package com.example.gitapp.di

import com.example.gitapp.data.remote.api.ApiHelper
import com.example.gitapp.data.remote.api.ApiHelperImpl
import org.koin.dsl.module

val appModule = module {

    single<ApiHelper> { return@single ApiHelperImpl(get()) }

}
